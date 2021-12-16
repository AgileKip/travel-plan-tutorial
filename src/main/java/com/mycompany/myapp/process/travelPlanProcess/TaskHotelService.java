package com.mycompany.myapp.process.travelPlanProcess;

import com.mycompany.myapp.repository.TravelPlanProcessRepository;
import com.mycompany.myapp.service.TravelPlanService;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import com.mycompany.myapp.service.mapper.TravelPlanProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskHotelService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskHotelMapper taskHotelMapper;

    private final TravelPlanProcessMapper travelPlanProcessMapper;

    public TaskHotelService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskHotelMapper taskHotelMapper,
        TravelPlanProcessMapper travelPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskHotelMapper = taskHotelMapper;
        this.travelPlanProcessMapper = travelPlanProcessMapper;
    }

    public TaskHotelContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskHotelMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskHotelContextDTO taskHotelContext = new TaskHotelContextDTO();
        taskHotelContext.setTaskInstance(taskInstanceDTO);
        taskHotelContext.setTravelPlanProcess(travelPlanProcess);

        return taskHotelContext;
    }

    public TaskHotelContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskHotelContextDTO taskHotelContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskHotelContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskHotelContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskHotelContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskHotelContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setHotelName(taskHotelContext.getTravelPlanProcess().getTravelPlan().getHotelName());
        travelPlanDTO.setHotelBookingNumber(taskHotelContext.getTravelPlanProcess().getTravelPlan().getHotelBookingNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskHotelContextDTO taskHotelContext) {
        save(taskHotelContext);
        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskHotelContext.getTravelPlanProcess().getProcessInstance().getId())
            .map(travelPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskHotelContext.getTaskInstance(), travelPlanProcess);
    }
}
