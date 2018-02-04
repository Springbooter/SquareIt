package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = SquareDto.Builder.class)
public class SquareDto {

  private final int number;

  private SquareDto(Builder builder) {
    number = builder.number;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Buildern.
   * @param copy copy
   * @return return
   */
  public static Builder newBuilder(SquareDto copy) {
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

    public SquareDto build() {
      return new SquareDto(this);
    }
  }
}
