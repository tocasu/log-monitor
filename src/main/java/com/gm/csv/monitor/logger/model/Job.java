package com.gm.csv.monitor.logger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor
public class Job {

    private Integer pid;

    private String description;

    private LocalTime timeStart;

    private LocalTime timeEnd;
    public long computeDuration(){
        return timeStart == null || timeEnd == null ? 0 : timeStart.until(timeEnd, ChronoUnit.SECONDS);
    }

    public static Job fromLogEntries(LogEntry startLog, LogEntry endLog){
        return new Job(startLog.pid(), startLog.description(), startLog.timestamp(), endLog == null ? null : endLog.timestamp());
    }
}
