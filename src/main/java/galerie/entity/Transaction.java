/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.Date;
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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false)
    @NonNull
    private Date venduLe;

    @Column(unique = false)
    @NonNull
    private float prixVente;

    public Transaction(Integer id, Date venduLe, float prixVente, Tableau oeuvre, Exposition lieuDeVente, Personne client) {
        this.id = id;
        this.venduLe = venduLe;
        this.prixVente = prixVente;
        this.oeuvre = oeuvre;
        this.lieuDeVente = lieuDeVente;
        this.client = client;
    }
    
    @OneToOne  //relation tableau
    private Tableau oeuvre;
    
    @ManyToOne //relation exposition
    private Exposition lieuDeVente;
    
    @ManyToOne //relation exposition
    private Personne client;
    
}

