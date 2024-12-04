package it.epicode.gestione_eventi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(name = "data_evento",nullable = false)
    private LocalDate dataEvento;

    @Column(nullable = false)
    private String descrizione;

    @Column(name = "tipo_evento", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventoEnum tipoEvento;

    @Column(name = "num_max_partecipanti",nullable = false)
    private int numeroMassimoPartecipanti;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "evento")
    @JoinColumn(name = "partecipazioni_id")
    private Partecipazione partecipazione;
}
