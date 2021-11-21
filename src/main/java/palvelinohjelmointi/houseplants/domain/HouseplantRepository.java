package palvelinohjelmointi.houseplants.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HouseplantRepository extends CrudRepository<Houseplant, Long> {

	List<Houseplant> findByName(String name);
}
