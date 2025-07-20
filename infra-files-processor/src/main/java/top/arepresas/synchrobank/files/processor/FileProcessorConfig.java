package top.arepresas.synchrobank.files.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.arepresas.quicken4j.QIFReader;

@Configuration
public class FileProcessorConfig {

  @Bean
  public QIFReader qifReader() {
    return new QIFReader();
  }
}