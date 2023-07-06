package tn.esprit.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Declaration;
import tn.esprit.entities.Role;
import tn.esprit.entities.TypePropriete;
import tn.esprit.entities.Utilisateur;
import tn.esprit.repositories.DeclarationRepo;
import tn.esprit.repositories.ProprieteRepo;
import tn.esprit.repositories.UtilisateurRepo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Services implements IServices {

    @Autowired
    DeclarationRepo declarationRepo;
    @Autowired
    ProprieteRepo proprieteRepo;
    @Autowired
    UtilisateurRepo utilisateurRepo;

    @Override
    public Utilisateur ajouterVictime(Utilisateur victime) {
        if (victime.getRole().equals(Role.VICTIME))
            return utilisateurRepo.save(victime);
        else
            return new Utilisateur();
    }

    @Override
    public String ajouterPoliciers(List<Utilisateur> policiers) {
        int compteur = 0;
        for (Utilisateur u : policiers) {
            if (u.getRole().equals(Role.POLICIER)) {
                utilisateurRepo.save(u);
                compteur++;
            }
        }
        return compteur + " policiers sont ajoutés avec succès !";
    }

    @Override
    public String ajouterDeclarationEtAffecterAVictime(Declaration declaration, long telephone) {
        Utilisateur utilisateur = utilisateurRepo.findByTelephone(telephone);
        if (utilisateur.getRole().equals(Role.VICTIME)) {
            // Declaration est le parent / Propriete est le child ==> on active le cascade et on supprime @JsonIgnore
            // Victime est child et declaration est le parent
            // On affacte le child au parent (Victime--declaration)
            declaration.setVictime(utilisateur);
            declaration.setEstTraitee(false);
            declarationRepo.save(declaration);
            return "Declaration ajouté avec succès";
        }
        return "Merci de saisir un telephone d'une victime";
    }

    @Override
    public void affecterPolicierADeclarataion(long idUtilisateur, long idDeclarataion) {
        Utilisateur u = utilisateurRepo.findById(idUtilisateur).get(); //Child
        Declaration d = declarationRepo.findById(idDeclarataion).get(); //Parent
        if (u.getRole().equals(Role.POLICIER)) {
            //On affacte le child au parent
            d.setPolicier(u);
            declarationRepo.save(d);
        } else {
            log.info("Il faut saisir un identifiant valide !");
        }
    }

    @Override
    public void traiterDeclarationAutomatiquement() {
        long diff = 0;
        for (Declaration dec : declarationRepo.findAll()) {
            diff = calculDiff(new Date(), dec.getDateDeclaration());
            log.info("La différance => "+ diff);
            if (diff >= 30) {
                dec.setDateTraitement(new Date());
                dec.setEstTraitee(true);
                declarationRepo.save(dec);
            }
        }
    }

    @Override
    public List<Utilisateur> afficherDeclarationsTraitees() {
        return utilisateurRepo.findByDeclarationsPolicierEstTraiteeAndDeclarationsPolicierProprieteType(true, TypePropriete.VEHICULE);
    }

    public long calculDiff(Date date1, Date date2) {
        long diffInMillies = date1.getTime() - date2.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
