package tn.esprit.services;

import tn.esprit.entities.Declaration;
import tn.esprit.entities.Utilisateur;

import java.util.List;

public interface IServices {

     Utilisateur ajouterVictime (Utilisateur victime);
     String ajouterPoliciers(List<Utilisateur> policiers);
     String ajouterDeclarationEtAffecterAVictime(Declaration declaration, long cin);
     void affecterPolicierADeclarataion(long idUtilisateur,long idDeclarataion);
     void traiterDeclarationAutomatiquement();
     List<Utilisateur> afficherDeclarationsTraitees();
}
