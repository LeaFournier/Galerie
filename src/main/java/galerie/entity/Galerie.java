package galerie.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Galerie {
    
    private float chiffreAffairesAnnuel;
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @Column(unique=true)
    @NonNull
    private String adresse;

    public Galerie(Integer id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }
    
    
    // TODO : Mettre en oeuvre la relation oneToMany vers Exposition
     @OneToMany (mappedBy = "organisateur") //relation exposition
    private List<Exposition> evenements = new LinkedList<>();
     public List<Exposition> getExposition(){
         return evenements;
     }
     
     
     public float CAannuel(int annee){
         this.chiffreAffairesAnnuel = 0;
         for (Exposition e : evenements){
             if (e.getDateDebut().getYear() == annee){
                 this.chiffreAffairesAnnuel += e.CA(this.id);
             }
         }
      return this.chiffreAffairesAnnuel;
     }
 
}

