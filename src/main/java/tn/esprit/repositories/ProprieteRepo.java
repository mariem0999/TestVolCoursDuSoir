package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Propriete;

@Repository
public interface ProprieteRepo extends CrudRepository<Propriete, Long> {
}
