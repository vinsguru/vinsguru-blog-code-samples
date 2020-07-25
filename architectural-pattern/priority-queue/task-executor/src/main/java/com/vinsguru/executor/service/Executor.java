package com.vinsguru.executor.service;

import com.vinsguru.model.Task;
import org.redisson.api.RPriorityBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Executor {

    @Autowired
    private RPriorityBlockingQueue<Task> priorityQueue;

    @Autowired
    private FibService fibService;

    @Scheduled(fixedDelay = 1000)
    public void runTask() throws InterruptedException {
        System.out.println("----------------------------------------");
        Task task = this.priorityQueue.take();
        System.out.println("Priority : " + task.getPriority());
        System.out.println("Input    : " + task.getNumber());
        System.out.println("Result   : " + this.fibService.calculate(task.getNumber()));
    }

}
