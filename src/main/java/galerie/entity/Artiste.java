package galerie.entity;


import galerie.entity.Personne;
import galerie.entity.Tableau;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author leabf
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Artiste extends Personne{
    
    @Column(unique=true)
    @NonNull
    private String biographie;
    
    public Artiste (String nom, String adresse){
        super (nom, adresse);
    }
    
    @OneToMany (mappedBy = "auteur") //relation artiste tableau
    private List<Tableau> oeuvres = new LinkedList<>();
    
}
