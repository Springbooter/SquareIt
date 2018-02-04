package square.app.service;

import static com.google.common.base.Preconditions.checkArgument;

import square.app.dao.jpa.NumberRepository;
import square.app.domain.jpa.SquareNumber;
import square.app.exceptions.NumberNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumberServiceImpl implements NumberService {

  private static Logger log = LoggerFactory.getLogger(NumberServiceImpl.class);

  private final NumberRepository numberRepository;

  @Autowired
  public NumberServiceImpl(NumberRepository numberRepository) {
    this.numberRepository = numberRepository;
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
  public Long saveNumber(int number) {
    SquareNumber squareNumber = new SquareNumber();
    squareNumber.setNumber(number);

    return numberRepository.save(squareNumber).getId();
  }

  @Override
  public SquareNumber getNumberById(Long id) {
    checkArgument(id != null, "id of the number must not be null or empty");

    SquareNumber sqN = numberRepository.findOne(id);

    if (sqN == null) {
      log.debug("SquareNumber object with id = {} does not exists in database", id);
      throw new NumberNotFoundException(SquareNumber.class, id);
    }

    return sqN;
  }
}
