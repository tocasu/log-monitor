package com.gm.csv.monitor.logger.service;

import com.gm.csv.monitor.logger.model.LogEntry;
import com.gm.csv.monitor.logger.model.LogType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Component
public class LogEntryReader {

    private static final String COMMA_DELIMITER = ",";

    private List<LogEntry> entries;

    public LogEntryReader read(File file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()))) {
            entries = reader.lines()
                    .map(line -> toLogEntry(line.split(COMMA_DELIMITER))).filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return this;
    }

    private LogEntry toLogEntry(String[] row) {
        try {
            LocalTime timestamp = LocalTime.parse(row[0]);
            String description = row[1];
            LogType type = LogType.START.toString().equals(row[2]) ? LogType.START : LogType.END;
            Integer pid = Integer.parseInt(row[3]);
            return new LogEntry(timestamp, description, type, pid);
        } catch (Exception e) {
            return null;
        }
    }
}
