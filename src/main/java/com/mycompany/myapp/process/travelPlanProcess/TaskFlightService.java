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
public class TaskFlightService {

    private final TaskInstanceService taskInstanceService;

    private final TravelPlanService travelPlanService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final TravelPlanProcessRepository travelPlanProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskFlightMapper taskFlightMapper;

    private final TravelPlanProcessMapper travelPlanProcessMapper;

    public TaskFlightService(
        TaskInstanceService taskInstanceService,
        TravelPlanService travelPlanService,
        TaskInstanceRepository taskInstanceRepository,
        TravelPlanProcessRepository travelPlanProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskFlightMapper taskFlightMapper,
        TravelPlanProcessMapper travelPlanProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.travelPlanService = travelPlanService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.travelPlanProcessRepository = travelPlanProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskFlightMapper = taskFlightMapper;
        this.travelPlanProcessMapper = travelPlanProcessMapper;
    }

    public TaskFlightContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskFlightMapper::toTravelPlanProcessDTO)
            .orElseThrow();

        TaskFlightContextDTO taskFlightContext = new TaskFlightContextDTO();
        taskFlightContext.setTaskInstance(taskInstanceDTO);
        taskFlightContext.setTravelPlanProcess(travelPlanProcess);

        return taskFlightContext;
    }

    public TaskFlightContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskFlightContextDTO taskFlightContext) {
        TravelPlanDTO travelPlanDTO = travelPlanService
            .findOne(taskFlightContext.getTravelPlanProcess().getTravelPlan().getId())
            .orElseThrow();
        travelPlanDTO.setName(taskFlightContext.getTravelPlanProcess().getTravelPlan().getName());
        travelPlanDTO.setStartDate(taskFlightContext.getTravelPlanProcess().getTravelPlan().getStartDate());
        travelPlanDTO.setEndDate(taskFlightContext.getTravelPlanProcess().getTravelPlan().getEndDate());
        travelPlanDTO.setAirlineCompanyName(taskFlightContext.getTravelPlanProcess().getTravelPlan().getAirlineCompanyName());
        travelPlanDTO.setAirlineTicketNumber(taskFlightContext.getTravelPlanProcess().getTravelPlan().getAirlineTicketNumber());
        travelPlanService.save(travelPlanDTO);
    }

    public void complete(TaskFlightContextDTO taskFlightContext) {
        save(taskFlightContext);
        TravelPlanProcessDTO travelPlanProcess = travelPlanProcessRepository
            .findByProcessInstanceId(taskFlightContext.getTravelPlanProcess().getProcessInstance().getId())
            .map(travelPlanProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskFlightContext.getTaskInstance(), travelPlanProcess);
    }
}
