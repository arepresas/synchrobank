package top.arepresas.synchrobank.files.processor;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.arepresas.synchrobank.database.entity.NotificationEntity;
import top.arepresas.synchrobank.database.entity.NotificationStatus;
import top.arepresas.synchrobank.database.repository.NotificationRepository;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationsJob {

  private final NotificationRepository notificationRepository;
  private final NotificationService notificationService;

  @Scheduled(cron = "${job-cron-notifications-exec}", zone = "Europe/Paris")
  public void processNotifications() {

    final List<NotificationEntity> notificationsToProcess =
      notificationRepository.findAllByStatus(NotificationStatus.PENDING);

    final List<FunctionalError> errors = notificationsToProcess.stream()
      .map(notificationService::processNotification)
      .filter(Either::isLeft)
      .flatMap(either -> either.getLeft().stream())
      .toList();

    if (!errors.isEmpty()) {
      log.error("Encountered {} errors during pending notifications processing: {}", errors.size(), errors);
    } else {
      log.info("Pending notifications processing completed successfully for {} notifications",
        notificationsToProcess.size());
    }
  }
}

