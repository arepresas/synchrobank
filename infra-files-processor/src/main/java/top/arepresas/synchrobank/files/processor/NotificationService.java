package top.arepresas.synchrobank.files.processor;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.arepresas.synchrobank.database.entity.NotificationEntity;
import top.arepresas.synchrobank.database.repository.NotificationRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;
  private final QifFileProcessorService qifFileProcessorService;

  public Either<List<FunctionalError>, Void> processNotification(NotificationEntity notification) {
    return switch (notification.getType()) {
      case QIF_FILE -> processQifFile(notification);
      case CSV_FILE -> Either.left(List.of(
        FunctionalError.builder().message("Unsupported notification type: " + notification.getType()).build()));
    };
  }

  private Either<List<FunctionalError>, Void> processQifFile(final NotificationEntity notification) {

    final Either<List<FunctionalError>, Void> processingResult = qifFileProcessorService.processFile(notification.getMessage());

    processingResult
      .peek(success -> notification.isSent())
      .peekLeft(errors -> notification.isFailed());

    notificationRepository.save(notification);

    return processingResult;
  }
}
