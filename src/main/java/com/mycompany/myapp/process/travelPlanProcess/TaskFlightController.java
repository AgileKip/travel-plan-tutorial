package com.mycompany.myapp.process.travelPlanProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-plan-process/task-flight")
public class TaskFlightController {

    private final Logger log = LoggerFactory.getLogger(TaskFlightController.class);

    private final TaskFlightService taskFlightService;

    public TaskFlightController(TaskFlightService taskFlightService) {
        this.taskFlightService = taskFlightService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskFlightContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskFlightContextDTO taskFlightContext = taskFlightService.loadContext(id);
        return ResponseEntity.ok(taskFlightContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskFlightContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskFlightContextDTO taskFlightContext = taskFlightService.claim(id);
        return ResponseEntity.ok(taskFlightContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskFlightContextDTO taskFlightContext) {
        log.debug("REST request to complete TravelPlanProcess.TaskFlight {}", taskFlightContext.getTaskInstance().getId());
        taskFlightService.complete(taskFlightContext);
        return ResponseEntity.noContent().build();
    }
}
