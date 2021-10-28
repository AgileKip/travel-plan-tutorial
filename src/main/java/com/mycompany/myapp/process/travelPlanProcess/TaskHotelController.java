package com.mycompany.myapp.process.travelPlanProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-plan-process/task-hotel")
public class TaskHotelController {

    private final Logger log = LoggerFactory.getLogger(TaskHotelController.class);

    private final TaskHotelService taskHotelService;

    public TaskHotelController(TaskHotelService taskHotelService) {
        this.taskHotelService = taskHotelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskHotelContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskHotelContextDTO taskHotelContext = taskHotelService.loadContext(id);
        return ResponseEntity.ok(taskHotelContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskHotelContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskHotelContextDTO taskHotelContext = taskHotelService.claim(id);
        return ResponseEntity.ok(taskHotelContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskHotelContextDTO taskHotelContext) {
        log.debug("REST request to complete TravelPlanProcess.TaskHotel {}", taskHotelContext.getTaskInstance().getId());
        taskHotelService.complete(taskHotelContext);
        return ResponseEntity.noContent().build();
    }
}
