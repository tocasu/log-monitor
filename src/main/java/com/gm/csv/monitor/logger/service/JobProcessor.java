package com.gm.csv.monitor.logger.service;

import com.gm.csv.monitor.logger.model.Job;
import com.gm.csv.monitor.logger.model.LogEntry;
import com.gm.csv.monitor.logger.model.LogType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class JobProcessor {

    private List<Job> jobs;
    public JobProcessor convertLogEntries(List<LogEntry> logEntries) {
        List<LogEntry> endLogs = logEntries.stream().filter(entry -> LogType.END.equals(entry.type())).collect(Collectors.toList());
        jobs = logEntries.stream().filter(entry -> LogType.START.equals(entry.type()))
                .map(startLog -> Job.fromLogEntries(startLog, findEndLog(startLog, endLogs)))
                .collect(Collectors.toList());
        return this;
    }

    private static LogEntry findEndLog(LogEntry startLog, List<LogEntry> endLogs) {
        return endLogs.stream().filter(endLog -> startLog.pid().equals(endLog.pid())).findFirst().orElse(null);
    }
}
