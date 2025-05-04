package com.gm.csv.monitor.logger.model;

import java.time.LocalTime;

public record LogEntry(LocalTime timestamp, String description, LogType type, Integer pid) {}
