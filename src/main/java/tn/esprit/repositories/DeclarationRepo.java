package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Declaration;

@Repository
public interface DeclarationRepo extends CrudRepository<Declaration, Long> {
}
