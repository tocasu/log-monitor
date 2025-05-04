package com.gm.csv.monitor.logger.service;

import com.gm.csv.monitor.logger.model.LogEntry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MonitorService {

    private final LogEntryReader reader;

    public List<LogEntry> analyze( File file) throws IOException {
        return reader.read(file).getEntries();
    }
}
