package com.mycompany.myapp.process.travelPlanProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-plan-process/task-car")
public class TaskCarController {

    private final Logger log = LoggerFactory.getLogger(TaskCarController.class);

    private final TaskCarService taskCarService;

    public TaskCarController(TaskCarService taskCarService) {
        this.taskCarService = taskCarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCarContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskCarContextDTO taskCarContext = taskCarService.loadContext(id);
        return ResponseEntity.ok(taskCarContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskCarContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskCarContextDTO taskCarContext = taskCarService.claim(id);
        return ResponseEntity.ok(taskCarContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskCarContextDTO taskCarContext) {
        log.debug("REST request to complete TravelPlanProcess.TaskCar {}", taskCarContext.getTaskInstance().getId());
        taskCarService.complete(taskCarContext);
        return ResponseEntity.noContent().build();
    }
}
