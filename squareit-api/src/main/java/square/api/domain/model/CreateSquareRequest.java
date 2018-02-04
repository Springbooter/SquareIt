package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CreateSquareRequest.Builder.class)
public class CreateSquareRequest {

  @NotNull(message = "Number must not be empty")
  @Valid
  private final int number;

  private CreateSquareRequest(Builder builder) {
    number = builder.number;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Builder.
   * @param copy copy
   * @return return
   */
  public static Builder newBuilder(CreateSquareRequest copy) {
    Builder builder = new Builder();
    builder.number = copy.getNumber();
    return builder;
  }

  public int getNumber() {
    return number;
  }

  public static final class Builder {
    private int number;

    private Builder() {
    }

    public Builder withNumber(int val) {
      number = val;
      return this;
    }

    public CreateSquareRequest build() {
      return new CreateSquareRequest(this);
    }
  }
}
