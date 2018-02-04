package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = GetSquareResponse.Builder.class)
public class GetSquareResponse {

  private SquareDto squareDto;

  private GetSquareResponse(Builder builder) {
    squareDto = builder.squareDto;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Buildern.
   * @param copy copy
   * @return SquareDto
   */
  public static Builder newBuilder(GetSquareResponse copy) {
    Builder builder = new Builder();
    builder.squareDto = copy.getSquareDto();
    return builder;
  }

  public SquareDto getSquareDto() {
    return squareDto;
  }

  public static final class Builder {
    private SquareDto squareDto;

    private Builder() {
    }

    public Builder withSquareDto(SquareDto val) {
      squareDto = val;
      return this;
    }

    public GetSquareResponse build() {
      return new GetSquareResponse(this);
    }
  }
}
