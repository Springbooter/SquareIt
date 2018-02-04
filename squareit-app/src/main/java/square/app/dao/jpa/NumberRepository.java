package square.app.dao.jpa;

import org.springframework.data.repository.CrudRepository;

import square.app.domain.jpa.SquareNumber;

public interface NumberRepository extends CrudRepository<SquareNumber, Long> {
}
