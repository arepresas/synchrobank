package top.arepresas.synchrobank.files.processor;

import io.vavr.control.Either;

import java.util.List;

public interface FileProcessorService {
  Either<List<FunctionalError>, Void> processFile(String filePath);
}
