package square.app.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static square.api.domain.errors.ErrorCode.*;

import square.api.domain.errors.ErrorCode;
import square.api.domain.errors.ErrorInfo;
import square.api.domain.model.CreateSquareRequest;
import square.api.domain.model.CreateSquareResponse;
import square.api.domain.model.GetSquareResponse;
import square.app.BaseSpringBootTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class SquareItRestControllerTest extends BaseSpringBootTest {

  private static final String NUMBER_URL = "/rest/square/number";

  @Before
  public void setUp() {
    tearDown();
  }

  @After
  public void tearDown() {
    numberRepository.deleteAll();
  }

  @Test
  public void saveNumber_ShouldWork_SaveANumber() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number = 1;

    CreateSquareRequest.Builder request = createCorrectNumberRequest(number);
    CreateSquareResponse response = callCreateNumberPostOK(OK, request);

    Long id = response.getNumberId();

    assertThat(numberRepository.findAll()).hasSize(1);
    assertThat(numberRepository.findOne(id).getNumber()).isEqualTo(number);
  }

  @Test
  public void saveNumber_ShouldWork_SaveManyNumbers() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number1 = 1;
    int number2 = 2;

    CreateSquareRequest.Builder request1 = createCorrectNumberRequest(number1);
    CreateSquareRequest.Builder request2 = createCorrectNumberRequest(number2);

    CreateSquareResponse response1 = callCreateNumberPostOK(OK, request1);
    CreateSquareResponse response2 = callCreateNumberPostOK(OK, request2);

    Long id1 = response1.getNumberId();
    Long id2 = response2.getNumberId();

    assertThat(numberRepository.findAll()).hasSize(2);
    assertThat(numberRepository.findOne(id1).getNumber()).isEqualTo(number1);
    assertThat(numberRepository.findOne(id2).getNumber()).isEqualTo(number2);
  }

  @Test
  public void getNumber_ShouldWork_GetANumber() {
    assertThat(numberRepository.findAll()).hasSize(0);

    int number = 9;

    CreateSquareRequest.Builder request = createCorrectNumberRequest(number);
    CreateSquareResponse responseCreate = callCreateNumberPostOK(OK, request);

    Long id = responseCreate.getNumberId();

    assertThat(numberRepository.findAll()).hasSize(1);
    assertThat(numberRepository.findOne(id).getNumber()).isEqualTo(number);

    GetSquareResponse responseGet = callGetNumberGetOK(OK, id);

    assertThat(responseGet).isNotNull();
    assertThat(responseGet.getSquareDto().getNumber()).isEqualTo((int) Math.pow(number, 2));
  }

  @Test
  public void getNumber_ShouldFail_IdNumberDoesNotExist() {
    Long id = 15L;

    ErrorInfo responseGetError = callGetNumberGetError(NOT_FOUND, id);

    checkErrorResponse(responseGetError, NUMBER_NOT_FOUND,
        "SquareNumber with id = '" + id + "' does not exist");
  }

  private CreateSquareResponse callCreateNumberPostOK(HttpStatus expectedStatus, CreateSquareRequest.Builder request) {
    return httpPost(NUMBER_URL + "/v1/setNumber", expectedStatus, request.build(), CreateSquareResponse.class);
  }

  private GetSquareResponse callGetNumberGetOK(HttpStatus expectedStatus, Long id) {
    return httpGet(NUMBER_URL + "/v1/getNumber/" + id, expectedStatus, GetSquareResponse.class);
  }

  private ErrorInfo callGetNumberGetError(HttpStatus expectedStatus, Long id) {
    return httpGetError(NUMBER_URL + "/v1/getNumber/" + id, expectedStatus, ErrorInfo.class);
  }

  private void checkErrorResponse(ErrorInfo response, ErrorCode code, String expectedDescription) {
    assertThat(response).isNotNull();
    assertThat(response.getErrorCode()).isEqualTo(code);
    assertThat(response.getErrorDescription()).isEqualTo(expectedDescription);
  }

  private CreateSquareRequest.Builder createCorrectNumberRequest(int number) {
    return CreateSquareRequest.newBuilder().withNumber(number);
  }
}
