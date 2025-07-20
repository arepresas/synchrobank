package top.arepresas.synchrobank.files.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.arepresas.quicken4j.QIFReader;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QifFileProcessorService {
  private final QIFReader qifReader;

  public void processFile(String filePath) throws IOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
