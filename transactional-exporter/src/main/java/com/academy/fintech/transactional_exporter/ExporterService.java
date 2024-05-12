package com.academy.fintech.transactional_exporter;

import com.academy.fintech.transactional_exporter.db.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Component
@Service
@RequiredArgsConstructor
public class ExporterService {

    private final KafkaTemplate<Integer, Transaction> kafkaTemplate;

    @Value("${exporter.transaction.topic}")
    private String transactionTopicName;

    public CompletableFuture<SendResult<Integer, Transaction>> export(Transaction transaction) {
        return kafkaTemplate.send(transactionTopicName, transaction.getKey(), transaction);
    }
}
