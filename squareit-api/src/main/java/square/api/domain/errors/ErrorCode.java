package square.api.domain.errors;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ErrorCode {

  NUMBER_NOT_FOUND,
  VALIDATION_ERROR_REQUEST_PARAM;

  @JsonCreator
  public static ErrorCode forValue(String value) {
    return ErrorCode.valueOf(value);
  }
}
