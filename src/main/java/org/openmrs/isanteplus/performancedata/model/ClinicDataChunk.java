package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.model.connection.DataManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class ClinicDataChunk {

    private List<Person> people;

    private List<Patient> patients;

    private List<Visit> visits;

    private List<Encounter> encounters;

    private List<Obs> observations;

    public ClinicDataChunk() {
        initCollections();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void addAllPeople(Collection<Person> persons) {
        this.people.addAll(persons);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAllPatients(Collection<Patient> patients) {
        this.patients.addAll(patients);
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public void addAllVisits(Collection<Visit> visits) {
        this.visits.addAll(visits);
    }

    public void addEncounter(Encounter encounter) {
        encounters.add(encounter);
    }

    public void addAllEncounters(Collection<Encounter> encounters) {
        this.encounters.addAll(encounters);
    }

    public void addObs(Obs obs) {
        observations.add(obs);
    }

    public void addAllObses(Collection<Obs> observations) {
        this.observations.addAll(observations);
    }

    public void insertAndFlush(DataManager dataManager) {
        insertAll(dataManager);
        initCollections();
    }

    public void insertAll(DataManager dataManager) {
        dataManager.insertEntities(new ArrayList<>(people));
        dataManager.insertEntities(new ArrayList<>(patients));
        dataManager.insertEntities(new ArrayList<>(visits));
        dataManager.insertEntities(new ArrayList<>(encounters));
        dataManager.insertEntities(new ArrayList<>(observations));
    }

    private void initCollections() {
        people = new ArrayList<>();
        patients = new ArrayList<>();
        visits = new ArrayList<>();
        encounters = new ArrayList<>();
        observations = new ArrayList<>();
    }
}
