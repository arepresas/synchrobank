package top.arepresas.synchrobank.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.arepresas.synchrobank.database.entity.NotificationEntity;
import top.arepresas.synchrobank.database.entity.NotificationStatus;
import top.arepresas.synchrobank.database.entity.NotificationType;
import top.arepresas.synchrobank.database.repository.NotificationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

  private final NotificationRepository notificationRepository;

  @PostMapping("/uploadQif")
  public ResponseEntity<String> uploadQifFile(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().body("Please select a file to upload.");
    }

    Path tempFile;

    try {
      // 1. Create a temporary file to avoid name collisions
      tempFile = Files.createTempFile("upload-", "-" + file.getOriginalFilename());

      // 2. Transfer the content of the uploaded file to the temporary file
      file.transferTo(tempFile);
      log.info("File '{}' temporarily saved at: {}", file.getOriginalFilename(), tempFile.toAbsolutePath());

      NotificationEntity newFileNotificationEntity = NotificationEntity.builder()
        .type(NotificationType.QIF_FILE)
        .status(NotificationStatus.PENDING)
        .message(tempFile.toAbsolutePath().toString())
        .build();

      notificationRepository.save(newFileNotificationEntity);

      return ResponseEntity.ok("File processed successfully: " + file.getOriginalFilename());

    } catch (IOException e) {
      log.error("Failed to process file: {}", file.getOriginalFilename(), e);
      return ResponseEntity.internalServerError().body("Failed to process file: " + e.getMessage());
    }
  }
}