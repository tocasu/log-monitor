package com.gm.csv.monitor.logger.service;


import com.gm.csv.monitor.logger.config.Config;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class MonitorService {

    private final Config config;

    public String analyze(File file) {
        return null;
    }
}
