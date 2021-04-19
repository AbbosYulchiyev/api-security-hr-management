package uz.pdp.apisecurityhrmanagement.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apisecurityhrmanagement.payload.ApiResponse;
import uz.pdp.apisecurityhrmanagement.payload.TaskDTO;
import uz.pdp.apisecurityhrmanagement.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public HttpEntity<?> getTasksList() {
        return ResponseEntity.ok(taskService.getTasksList());
    }

    @PostMapping
    public HttpEntity<?> addTask(TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.addTask(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping(value = "/{taskId}")
    public HttpEntity<?> deleteTask(@PathVariable UUID taskId) {
        ApiResponse apiResponse = taskService.delete(taskId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

}
