package org.openmrs.isanteplus.performancedata.model.connection;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.Visit;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ClinicDataChunk {

    private Set<Person> people;

    private Set<Patient> patients;

    private Set<Visit> visits;

    private Set<Encounter> encounters;

    public ClinicDataChunk() {
        people = new HashSet<>();
        patients = new HashSet<>();
        visits = new HashSet<>();
        encounters = new HashSet<>();
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

    public void insertAll(Inserter ins) {
        ins.insertEntities(new HashSet<>(people));
        ins.insertEntities(new HashSet<>(patients));
        ins.insertEntities(new HashSet<>(visits));
        ins.insertEntities(new HashSet<>(encounters));
    }
}
