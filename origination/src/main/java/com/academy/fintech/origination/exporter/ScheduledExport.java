package com.academy.fintech.origination.exporter;

import com.academy.fintech.transactional_exporter.KafkaExporterService;
import com.academy.fintech.transactional_exporter.task.ExportTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduledExport {
    @Bean
    public ExportTask exportTask(@Autowired KafkaExporterService kafkaExporterService, @Autowired ExporterService exporterService) {
        return new ExportTask(kafkaExporterService, exporterService);
    }
}
