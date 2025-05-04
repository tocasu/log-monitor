package com.gm.csv.monitor.logger;

import com.gm.csv.monitor.logger.model.LogEntry;
import com.gm.csv.monitor.logger.service.MonitorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
	void givenLogFile_whenFileContainsRows_shouldReadAllRows() throws IOException {
		File file = this.workingDir.resolve("logs_valid.log").toFile();
		List<LogEntry> result = service.analyze(file);
		assertThat(result).hasSize(7);
	}

	@Test
	void givenLogFile_whenFileContainsInvalidRows_shouldReadAllValidRows() throws IOException {
		File file = this.workingDir.resolve("logs_invalid_rows.log").toFile();
		List<LogEntry> result = service.analyze(file);
		assertThat(result).hasSize(5);
	}

	@Test
	void givenLogFile_whenFileContainsEmptyRows_shouldSkipThoseRows() throws IOException {
		File file = this.workingDir.resolve("logs_blank_rows.log").toFile();
		List<LogEntry> result = service.analyze(file);
		assertThat(result).hasSize(7);
	}
}
