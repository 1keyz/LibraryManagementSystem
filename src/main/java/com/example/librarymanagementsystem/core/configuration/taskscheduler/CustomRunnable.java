package com.example.librarymanagementsystem.core.configuration.taskscheduler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomRunnable implements Runnable{
    private CustomControlTheBringTime bringTime;
    @Override
    public void run() {
        bringTime.threadPoolTaskSchedulerControlTheBringTime();
    }
}
