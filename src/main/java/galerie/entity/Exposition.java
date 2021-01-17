/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.time.LocalDate;
import java.util.Date;
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
public class Exposition {
    
    private float sommeVentes;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=false)
    @NonNull
    private LocalDate dateDebut;
    
    @Column(unique=false)
    @NonNull
    private String intitule;
    
    @Column(unique=false)
    @NonNull
    private int dureeExpo;

    public Exposition(Integer id,LocalDate dateDebut, String intitule, int dureeExpo, Galerie organisateur) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.intitule = intitule;
        this.dureeExpo = dureeExpo;
        this.organisateur = organisateur;
    }
    
    @ManyToMany
    private List<Tableau> oeuvres = new LinkedList<>();
    
    @ManyToOne //relation artiste
    private Galerie organisateur;
     
    @OneToMany (mappedBy = "lieuDeVente") //relation transaction
    private List<Transaction> ventes = new LinkedList<>();
    public List<Transaction> getTransactions(){
        return ventes;
    }
    
    public float CA(Integer id){
        this.sommeVentes = 0;
        for (Transaction v : ventes){
            if (v.getLieuDeVente().id == id){
                this.sommeVentes += v.getPrixVente();
            }
        }
        return this.sommeVentes;
    }
}
