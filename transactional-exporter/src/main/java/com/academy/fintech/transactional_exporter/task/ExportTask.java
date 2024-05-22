package com.academy.fintech.transactional_exporter.task;

import com.academy.fintech.transactional_exporter.KafkaExporterService;
import com.academy.fintech.transactional_exporter.db.Transaction;
import com.academy.fintech.transactional_exporter.db.TransactionService;
import com.academy.fintech.transactional_exporter.db.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class ExportTask<T extends Transaction> {
    private final KafkaExporterService kafkaExporterService;

    private final TransactionService<T> transactionService;

    @Scheduled(fixedRateString = "${exporter.export-seconds-rate}", timeUnit = SECONDS)
    public void runNewExport() {
        List<T> newTransactions = transactionService.findWithStatus(TransactionStatus.NEW);
        CompletableFuture[] futures = newTransactions.stream().map(transaction -> kafkaExporterService.export(transaction)).toArray(CompletableFuture[]::new);
        waitSendingEnd(newTransactions, futures, futures.length);
    }

    @Scheduled(fixedRateString = "${exporter.export-failed-seconds-rate}", timeUnit = SECONDS)
    public void runFailedExport() {
        List<T> newTransactions = transactionService.findWithStatus(TransactionStatus.ERROR);
        CompletableFuture[] futures = newTransactions.stream().map(transaction -> kafkaExporterService.export(transaction)).toArray(CompletableFuture[]::new);
        waitSendingEnd(newTransactions, futures, futures.length);
    }

    private void waitSendingEnd(List<T> transactions, CompletableFuture[] futures, int countSendingElements) {
        final long awaitTime = countSendingElements * 180000 + 60000;

        try {
            CompletableFuture.allOf(futures).get(awaitTime, MILLISECONDS);

        } catch (InterruptedException | ExecutionException | TimeoutException ignored) {

        } finally {
            for (int i = 0; i < countSendingElements; i++) {
                if (futures[i].isCancelled() || futures[i].isCompletedExceptionally() || !futures[i].isDone()) {
                    transactionService.updateStatusById(TransactionStatus.ERROR, transactions.get(i));
                }
            }
        }
    }
}
