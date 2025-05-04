package com.gm.csv.monitor.logger;

import com.gm.csv.monitor.logger.model.Job;
import com.gm.csv.monitor.logger.model.LogEntry;
import com.gm.csv.monitor.logger.service.LogEntryReader;
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
	LogEntryReader reader;
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
		List<LogEntry> result = reader.read(file).getEntries();
		assertThat(result).hasSize(7);
	}

	@Test
	void givenLogFile_whenFileContainsInvalidRows_shouldReadAllValidRows() throws IOException {
		File file = this.workingDir.resolve("logs_invalid_rows.log").toFile();
		List<LogEntry> result = reader.read(file).getEntries();
		assertThat(result).hasSize(5);
	}

	@Test
	void givenLogFile_whenFileContainsEmptyRows_shouldSkipThoseRows() throws IOException {
		File file = this.workingDir.resolve("logs_blank_rows.log").toFile();
		List<LogEntry> result = reader.read(file).getEntries();
		assertThat(result).hasSize(7);
	}

	@Test
	void givenLogFile_whenFileContainsRows_shouldGenerateOverdueJobs() throws IOException {
		File file = this.workingDir.resolve("logs_valid.log").toFile();
		List<Job> jobs = service.extractOverdueJobs(file);
		assertThat(jobs).hasSize(2);
	}

	@Test
	void givenLogFile_whenFileContainsRows_shouldGenerateOverdueMessages() throws IOException {
		File file = this.workingDir.resolve("logs_valid.log").toFile();
		List<Job> jobs = service.extractOverdueJobs(file);
		List<String> messages = service.analyzeJobs(jobs);
		assertThat(messages).hasSize(2); // one job don't have END log entry, and one job didn't exceed 5 minutes
	}
}
