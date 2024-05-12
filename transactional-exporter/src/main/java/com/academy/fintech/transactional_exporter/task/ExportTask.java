package com.academy.fintech.transactional_exporter.task;

import com.academy.fintech.transactional_exporter.ExporterService;
import com.academy.fintech.transactional_exporter.db.Transaction;
import com.academy.fintech.transactional_exporter.db.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class ExportTask {
    @Autowired
    private ExporterService exporterService;

    @Autowired
    private TransactionService<? extends Transaction> transactionService;

    @Scheduled(fixedRateString = "${exporter.export-seconds-rate}", timeUnit = SECONDS)
    public void runExport() {
        List<? extends Transaction> newTransactions = transactionService.findWithNewStatus();
        CompletableFuture[] futures = newTransactions.stream().map(transaction -> exporterService.export(transaction)).toArray(CompletableFuture[]::new);
        waitSendingEnd(CompletableFuture.allOf(futures), futures.length);
    }

    private void waitSendingEnd(CompletableFuture<Void> commonCompletableFuture, int countSendingElements) {
        // Wait max waiting value for kafka sending + one minute for serialization and mapping of data
        final long awaitTime = countSendingElements * 180000 + 60000;

        try {
            commonCompletableFuture.get(awaitTime, MILLISECONDS);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
