package com.gm.csv.monitor.logger;

import com.gm.csv.monitor.logger.config.Config;
import com.gm.csv.monitor.logger.service.MonitorService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.io.File;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = LoggerApplication.class)
class LoggerApplicationTests {

	@Autowired
	MonitorService service;

	private Path workingDir;

	@BeforeEach
	public void init() {
		this.workingDir = Path.of("", "src/test/resources/log");
	}

	@Test
	void givenLogFile_whenFileContainsRows_shouldAnalyzeItSuccesfully() {
		File file = this.workingDir.resolve("logs_valid.log").toFile();
		String result = service.analyze(file);
		assertThat(result).isNotNull();
	}
}
