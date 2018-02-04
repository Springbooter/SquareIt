package square.app.restcontroller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import square.api.domain.model.CreateSquareRequest;
import square.api.domain.model.CreateSquareResponse;
import square.api.domain.model.GetSquareResponse;
import square.api.domain.model.SquareDto;
import square.app.converters.SquareConverter;
import square.app.domain.jpa.SquareNumber;
import square.app.exceptions.ObjectNotFoundException;
import square.app.service.NumberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/square/number")
public class SquareItRestController extends BaseRestController {

  private static final Logger log = LoggerFactory.getLogger(SquareItRestController.class);

  private final NumberService numberService;

  /**
   * SquareItRestController rest service.
   * @param numberService numberService
   */
  @Autowired
  public SquareItRestController(final NumberService numberService) {
    this.numberService = numberService;
  }

  /**
   * Save a number rest-service.
   * @param request Requestobject from the service request.
   */
  @ApiOperation(value = "saveNumber",
      notes = "Saves a number in the database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Number saved"),
      @ApiResponse(code = 400, message = "Malformed request"),
      @ApiResponse(code = 404, message = "Number not found")})
  @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE,
      value = "/v1/setNumber")
  @ResponseBody
  public CreateSquareResponse saveNumber(@RequestBody @Valid final CreateSquareRequest request) {
    try {
      log.debug("Value that entered the rest-controller in saveNumber is: " + String.valueOf(request.getNumber()));
      final Long numberId = numberService.saveNumber(request.getNumber());

      log.debug("Value from the return of saveNumber has id: " + String.valueOf(numberId));
      return new CreateSquareResponse().withNumberId(numberId);
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot save number: {}. Exception {}.", request.getNumber(), e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when saving number: " + request.getNumber() + ".", e);
      throw e;
    }
  }

  /**
   * Get a number rest-service.
   */
  @ApiOperation(value = "getNumber",
      notes = "Get a number from the database.")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Number found"),
      @ApiResponse(code = 404, message = "Number not found")})
  @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/v1/getNumber/{number_id}")
  @ResponseBody
  public GetSquareResponse getNumber(@PathVariable("number_id") @Valid final Long id) {
    try {
      SquareNumber squareNumb = numberService.getNumberById(id);
      SquareDto squareDto = SquareConverter.toSquareNumberDto(squareNumb);

      return GetSquareResponse.newBuilder().withSquareDto(squareDto).build();
    } catch (IllegalArgumentException | ObjectNotFoundException e) {
      log.info("Cannot fetch number with id: {}. Exception {}", id, e.toString());
      throw e;
    } catch (RuntimeException e) {
      log.error("Error when fetching id: " + id, e);
      throw e;
    }
  }
}
