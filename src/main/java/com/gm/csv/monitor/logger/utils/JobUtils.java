package com.gm.csv.monitor.logger.utils;

import com.gm.csv.monitor.logger.model.Job;

public class JobUtils {
    public static String buildErrorMessage(Job job, long treshold) {
        return "Error: " + buildMessage(job, treshold);
    }

    public static String buildWarningMessage(Job job, long treshold) {
        return "Warning: " + buildMessage(job, treshold);
    }

    public static String buildMessage(Job job, long treshold) {
        return "job with id " + job.getPid() + " took not less than " + treshold / 60 + " minutes, exactly " + job.computeDuration() / 60 + " minutes";
    }
}
