package com.gm.csv.monitor.logger.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;


@Configuration
@Getter
public class Config {

    @Value("${csv.monitor.log.file}")
    private Resource filePath;

    @Value("${csv.monitor.duration.warning}")
    private Integer warningDuration;

    @Value("${csv.monitor.duration.error}")
    private Integer errorDuration;

    @Bean
    @Qualifier("logFile")
    public File file() throws IOException {
        return filePath.getFile();
    }

}
