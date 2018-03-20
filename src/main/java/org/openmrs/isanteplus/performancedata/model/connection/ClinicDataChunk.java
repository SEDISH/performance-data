package org.openmrs.isanteplus.performancedata.model.connection;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.Visit;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ClinicDataChunk {

    private Set<Person> persons;

    private Set<Patient> patients;

    private Set<Visit> visits;

    public ClinicDataChunk() {
        persons = new HashSet<>();
        patients = new HashSet<>();
        visits = new HashSet<>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addAllPersons(Collection<Person> persons) {
        this.persons.addAll(persons);
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

    public void insertAll(Inserter ins) {
        ins.insertEntities(new HashSet<>(persons));
        ins.insertEntities(new HashSet<>(patients));
        ins.insertEntities(new HashSet<>(visits));
    }
}
