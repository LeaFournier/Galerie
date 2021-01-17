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
public class Personne {
    
    private float budget;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false)
    @NonNull
    private String nom;

    @Column(unique = false)
    @NonNull
    private String adresse;

    public Personne(Integer id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    @OneToMany(mappedBy = "client") //relation transaction
    private List<Transaction> achats = new LinkedList<>();
    public List<Transaction> getTransactions(){
        return achats;
    }

    public float budgetAnnuel(int annee) {
        this.budget = 0;
        for (Transaction t : achats){
            if (t.getVenduLe().getYear()== annee && t.getClient().getId() == this.getId()){
                this.budget += t.getPrixVente();
            }
        }
        return this.budget;
    }
}
