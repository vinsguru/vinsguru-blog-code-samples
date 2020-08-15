package com.vinsguru.scheduler.controller;

import com.vinsguru.model.Priority;
import com.vinsguru.model.Task;
import org.redisson.api.RPriorityBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private RPriorityBlockingQueue<Task> priorityBlockingQueue;

    @GetMapping("/{priority}/{number}")
    public void schedule(@PathVariable String priority, @PathVariable int number){
        this.priorityBlockingQueue.add(this.getTask(priority, number));
    }

    private Task getTask(final String priority, final int number){
        return new Task(
                UUID.randomUUID(),
                Priority.valueOf(priority.toUpperCase()),
                number
        );
    }

}
