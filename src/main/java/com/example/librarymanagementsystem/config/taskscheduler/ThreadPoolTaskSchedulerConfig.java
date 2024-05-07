package com.example.librarymanagementsystem.config.taskscheduler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Calendar;
import java.util.Date;


@Configuration
@ComponentScan(basePackages = "com.example.librarymanagementsystem")
public class ThreadPoolTaskSchedulerConfig {
    private CustomControlTheBringTime bringTime;


    public ThreadPoolTaskSchedulerConfig(CustomControlTheBringTime bringTime ) {
        this.bringTime = bringTime;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date scheduledDate = cal.getTime();

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.initialize();
        scheduler.submit(new CustomRunnable(bringTime));
       // scheduler.schedule(new CustomRunnable(bringTime) , scheduledDate);

        return scheduler;
    }






}
