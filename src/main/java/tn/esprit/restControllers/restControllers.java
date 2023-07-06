package tn.esprit.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.entities.Declaration;
import tn.esprit.entities.Utilisateur;
import tn.esprit.services.IServices;

import java.util.List;

@RestController
public class restControllers {

    @Autowired
    IServices iServices;

    @PostMapping("ajouterVictime")
    Utilisateur ajouterVictime(@RequestBody Utilisateur victime) {
        return iServices.ajouterVictime(victime);
    }

    @PostMapping("ajouterPoliciers")
    public String ajouterPoliciers(@RequestBody List<Utilisateur> policiers) {
        return iServices.ajouterPoliciers(policiers);
    }

    @PostMapping("ajouterDeclarationEtAffecterAVictime")
    public String ajouterDeclarationEtAffecterAVictime(@RequestBody Declaration declaration, @RequestParam long cin) {
        return iServices.ajouterDeclarationEtAffecterAVictime(declaration, cin);
    }

    @PutMapping("affecterPolicierADeclarataion")
    public void affecterPolicierADeclarataion(long idUtilisateur, long idDeclarataion) {
        iServices.affecterPolicierADeclarataion(idUtilisateur, idDeclarataion);
    }

    @PutMapping("traiterDeclarationAutomatiquement")
    public void traiterDeclarationAutomatiquement() {
        iServices.traiterDeclarationAutomatiquement();
    }

    @GetMapping("afficherDeclarationsTraitees")
    public List<Utilisateur> afficherDeclarationsTraitees() {
        return iServices.afficherDeclarationsTraitees();
    }
}
