package square.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSquareResponse {

  private Long numberId;

  public CreateSquareResponse withNumberId(Long numberId) {
    this.numberId = numberId;
    return this;
  }

  public Long getNumberId() {
    return numberId;
  }

  public void setNumberId(Long numberId) {
    this.numberId = numberId;
  }
}
