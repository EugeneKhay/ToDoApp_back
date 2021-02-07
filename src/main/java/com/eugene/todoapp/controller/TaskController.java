package com.eugene.todoapp.controller;

import com.eugene.todoapp.domain.Task;
import com.eugene.todoapp.domain.TaskStatus;
import com.eugene.todoapp.repository.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.*;

@RestController
@RequestMapping("task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    // @GetMapping
    // public List<Task> getAllTasks() {
    //     return taskRepo.findAll();
    // }

    @GetMapping
    public List<String> getAllTasks() {
        List<String> lst = new ArrayList<String>();
        lst.add("One");
        lst.add("Two");
        lst.add("Three");
        return lst;
    }

    @GetMapping("{id}")
    public Task getSingleTask(@PathVariable("id") Task task) {
        return task;
    }

    @GetMapping("{status}")
    public List<Task> getTasksByStatus(@PathVariable("status") String s) {
        TaskStatus taskStatus = TaskStatus.valueOf(s);
        return taskRepo.findAllByStatus(taskStatus);
    }

    @PostMapping
    public Task addNewTask(@RequestBody Task task) {
        task.setTag("text");
        task.setCreationDate(Calendar.getInstance().getTime());
        task.setStatus(TaskStatus.NEW);
        return taskRepo.save(task);
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") Task taskFromDB,
                           @RequestBody Task task) {
        BeanUtils.copyProperties(task, taskFromDB, "id");
        return taskRepo.save(taskFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable("id") Task task) {
        taskRepo.delete(task);
    }
}
