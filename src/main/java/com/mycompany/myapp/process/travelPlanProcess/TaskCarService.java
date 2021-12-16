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
public class TaskCarService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskCarMapper taskCarMapper;

    private final TravelPlanProcessMapper travelPlanProcessMapper;

    public TaskCarService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskCarMapper taskCarMapper,
        TravelPlanProcessMapper travelPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskCarMapper = taskCarMapper;
        this.travelPlanProcessMapper = travelPlanProcessMapper;
    }

    public TaskCarContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskCarMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskCarContextDTO taskCarContext = new TaskCarContextDTO();
        taskCarContext.setTaskInstance(taskInstanceDTO);
        taskCarContext.setTravelPlanProcess(travelPlanProcess);

        return taskCarContext;
    }

    public TaskCarContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskCarContextDTO taskCarContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskCarContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskCarContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskCarContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskCarContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setCarCompanyName(taskCarContext.getTravelPlanProcess().getTravelPlan().getCarCompanyName());
        travelPlanDTO.setCarBookingNumber(taskCarContext.getTravelPlanProcess().getTravelPlan().getCarBookingNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskCarContextDTO taskCarContext) {
        save(taskCarContext);
        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskCarContext.getTravelPlanProcess().getProcessInstance().getId())
            .map(travelPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskCarContext.getTaskInstance(), travelPlanProcess);
    }
}
