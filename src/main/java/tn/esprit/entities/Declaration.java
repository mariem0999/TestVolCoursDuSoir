package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Declaration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idDeclaration;
    @Temporal(TemporalType.DATE)
    Date dateDeclaration;
    String description;
    boolean estTraitee;
    @Temporal(TemporalType.DATE)
    Date dateTraitement;
    @OneToOne(cascade = CascadeType.ALL)
    Propriete propriete;
    @ManyToOne
    @JsonIgnore
    Utilisateur victime;
    @ManyToOne
    @JsonIgnore
    Utilisateur policier;
}
