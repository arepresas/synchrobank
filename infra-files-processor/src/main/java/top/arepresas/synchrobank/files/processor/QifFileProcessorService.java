package top.arepresas.synchrobank.files.processor;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.arepresas.quicken4j.QIFReader;
import top.arepresas.quicken4j.Transactions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QifFileProcessorService implements FileProcessorService {
  private final QIFReader qifReader;

  @Override
  public Either<List<FunctionalError>, Void> processFile(String filePath) {

    log.info("Processing file {}", filePath);

    List<FunctionalError> errors = new ArrayList<>();

    try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
      final Transactions transactions = qifReader.read(inputStream);
      transactions.forEach(transaction -> {
        log.info("Date: {}, Amount: {}, Payee: {}, Memo: {}", transaction.getDate(), transaction.getAmount(), transaction.getPayee(), transaction.getMemo());
      });
    } catch (IOException e) {
      errors.add(FunctionalError.builder()
        .message("Failed to process file: " + filePath)
        .error(e.getMessage())
        .build());
      // TODO Save file to retry later
    } finally {
      // Ensure the temporary file is deleted after use
      try {
        Files.delete(Paths.get(filePath));
        log.info("Temporary file deleted: {}", filePath);
      } catch (IOException e) {
        errors.add(FunctionalError.builder()
          .message("Failed to delete temporary file: " + filePath)
          .error(e.getMessage())
          .build());
      }
    }

    return errors.isEmpty() ? Either.right(null) : Either.left(errors);
  }
}
