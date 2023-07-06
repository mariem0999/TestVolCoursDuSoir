package tn.esprit.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Propriete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idPropriete;
    @Enumerated(EnumType.STRING)
    TypePropriete type;
    String couleur;
    String marque;
    String matricule; // Vehicule
    String numSerie; // Bicyclette
}
