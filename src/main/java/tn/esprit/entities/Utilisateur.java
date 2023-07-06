package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUtilisateur;
    @Column(unique = true)
    long cin;
    String nom;
    String prenom;
    String adresse;
    long telephone;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "victime")
    @JsonIgnore
    List<Declaration> declarationsVictime;
    @OneToMany(mappedBy = "policier")
    @JsonIgnore
    List<Declaration> declarationsPolicier;
}
