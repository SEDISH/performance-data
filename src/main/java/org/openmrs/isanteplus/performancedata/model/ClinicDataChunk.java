package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;

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
        people = new ArrayList<>();
        patients = new ArrayList<>();
        visits = new ArrayList<>();
        encounters = new ArrayList<>();
        observations = new ArrayList<>();
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

    public void insertAll(Inserter ins) {
        ins.insertEntities(new ArrayList<>(people));
        ins.insertEntities(new ArrayList<>(patients));
        ins.insertEntities(new ArrayList<>(visits));
        ins.insertEntities(new ArrayList<>(encounters));
        ins.insertEntities(new ArrayList<>(observations));
    }
}
