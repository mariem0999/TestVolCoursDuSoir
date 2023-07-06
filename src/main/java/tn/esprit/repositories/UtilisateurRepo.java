package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.TypePropriete;
import tn.esprit.entities.Utilisateur;

import java.util.List;

@Repository
public interface UtilisateurRepo extends CrudRepository<Utilisateur, Long> {
    Utilisateur findByTelephone(long telephone);
    List<Utilisateur> findByDeclarationsPolicierEstTraiteeAndDeclarationsPolicierProprieteType(boolean traitee, TypePropriete type);
}
