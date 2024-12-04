package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.dao.EventoDAO;
import it.epicode.dao.LocationDAO;
import it.epicode.dao.PartecipazioneDAO;
import it.epicode.dao.PersonaDAO;
import it.epicode.gestione_eventi.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainDAO {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");

        EntityManager em = emf.createEntityManager();

        Faker faker = new Faker(new Locale("it"));

        //Persona
        PersonaDAO personaDAO = new PersonaDAO(em);
        Persona persona = new Persona();
        persona.setNome("Giulia");
        persona.setCognome("Maino");
        persona.setEmail("giuliamaino@gmail.com");
        persona.setDataDiNascita(LocalDate.of(1996, 12, 15));
        persona.setSesso(Sesso.DONNA);
//        persona.setListaPartecipazioni();

        personaDAO.insertPersona(persona);

        //Location
        LocationDAO locationDAO = new LocationDAO(em);
        Location location = new Location();

        location.setNome("Palasport");
        location.setCitta("Milano");

        locationDAO.insertLocation(location);

        //Evento
        EventoDAO eventoDAO = new EventoDAO(em);
        Evento evento = new Evento();
        evento.setTitolo("Epicode Party");
        evento.setDataEvento(LocalDate.of(2024, 12, 11));
        evento.setDescrizione("Super mega party!");
        evento.setTipoEvento(EventoEnum.PUBBLICO);
        evento.setNumeroMassimoPartecipanti(1000);
        evento.setLocation(location);
//        evento.setListaPartecipazioni();

        eventoDAO.insertEvento(evento);

        //Partecipazione
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);
        Partecipazione partecipazione = new Partecipazione();

        partecipazione.setEvento(evento);
        partecipazione.setPersona(persona);
        partecipazione.setStato(StatoPartecipazione.CONFERMATO);

        partecipazioneDAO.insertPartecipazione(partecipazione);

        em.close();
    }

}
