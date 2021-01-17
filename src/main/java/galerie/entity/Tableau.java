/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author leabf
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Tableau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String titre;

    @Column(unique=false)
    @NonNull
    private String support;

    @Column(unique=false)
    @NonNull
    private int largeur;

    @Column(unique=false)
    @NonNull
    private int hauteur;
    
    public Tableau (Integer id, String titre, String support, int largeur, int hauteur){
        this.id = id;
        this.titre = titre;
        this.support = support;
        this.largeur = largeur;
        this.hauteur = hauteur;
        
    }
    
    @ManyToMany (mappedBy = "oeuvres") //relation exposition
    private List<Exposition> accrochages = new LinkedList<>();
    
    @OneToOne(mappedBy = "oeuvre") //relation transaction
    private Transaction vendu;
    
    @ManyToOne //relation artiste
    private Artiste auteur;
    
}
