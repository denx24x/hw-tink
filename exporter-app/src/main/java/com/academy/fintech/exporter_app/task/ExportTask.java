package com.academy.fintech.exporter_app.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class ExportTask {

    @Scheduled(fixedRateString = "${exporter.export-seconds-rate}", timeUnit = SECONDS)
    public void export() {

    }
}
