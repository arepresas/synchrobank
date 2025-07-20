package top.arepresas.synchrobank.files.processor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FunctionalError {
  private final String message;
  private final String error;
}
