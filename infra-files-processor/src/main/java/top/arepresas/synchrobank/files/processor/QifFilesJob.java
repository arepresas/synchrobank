package top.arepresas.synchrobank.files.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.arepresas.quicken4j.QIFReader;
import top.arepresas.quicken4j.Transactions;
import top.arepresas.synchrobank.database.entity.NotificationEntity;
import top.arepresas.synchrobank.database.entity.NotificationStatus;
import top.arepresas.synchrobank.database.entity.NotificationType;
import top.arepresas.synchrobank.database.repository.NotificationRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class QifFilesJob {

  private final QIFReader qifReader;
  private final NotificationRepository notificationRepository;

  @Scheduled(cron = "${job-cron-file-exec}", zone = "Europe/Paris")
  public void processFile() {

    final List<NotificationEntity> notificationsToProcess =
      notificationRepository.findAllByStatusAndType(NotificationStatus.PENDING, NotificationType.QIF_FILE);

    notificationsToProcess.forEach(notification -> {
      try (InputStream inputStream = Files.newInputStream(Paths.get(notification.getMessage()))) {
        final Transactions transactions = qifReader.read(inputStream);
        transactions.forEach(transaction -> {
          log.info("Date: {}, Amount: {}, Payee: {}, Memo: {}", transaction.getDate(), transaction.getAmount(), transaction.getPayee(), transaction.getMemo());
        });
        notification.isSent();
      } catch (IOException e) {
        log.error("Failed to process file: {}", notification.getMessage(), e);
        notification.isFailed();
        // TODO Save file to retry later
      } finally {
        notificationRepository.save(notification);

        // Ensure the temporary file is deleted after use
        try {
          Files.delete(Paths.get(notification.getMessage()));
          log.info("Temporary file deleted: {}", notification.getMessage());
        } catch (IOException e) {
          log.error("Could not delete temporary file: {}", notification.getMessage(), e);
        }
      }
    });
  }
}

