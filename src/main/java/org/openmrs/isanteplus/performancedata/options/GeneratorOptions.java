package org.openmrs.isanteplus.performancedata.options;

import org.openmrs.isanteplus.performancedata.options.model.ClinicOption;
import org.openmrs.isanteplus.performancedata.options.model.PatientOption;
import org.openmrs.isanteplus.performancedata.options.service.CommandReader;
import org.openmrs.isanteplus.performancedata.options.util.CommandOption;
import org.openmrs.isanteplus.performancedata.options.util.CommandConstants;

import java.util.List;

public class GeneratorOptions {

    private CommandReader commandReader;

    private ClinicOption clinics;

    private PatientOption patients;

    public GeneratorOptions(String[] args) {
        commandReader = new CommandReader(args);
        clinics = new ClinicOption();
        patients = new PatientOption();

        setOptions();
    }

    public long getClinics() {
        return clinics.getClinics();
    }

    public long getPatients() {
        return patients.getPatients();
    }

    @Override
    public String toString() {
        return "GeneratorOptions{" +
                "clinics=" + clinics.getClinics() +
                ", patients=" + patients.getPatients() +
                '}';
    }

    private void setOptions() {
        List<CommandOption> options = commandReader.parseOptions();
        for (CommandOption option : options) {
            switch(option.getType()) {
                case CommandConstants.CLINICS_AMOUNT:
                    clinics.setClinics(Long.parseLong(option.getValue()));
                    break;
                case CommandConstants.PATIENTS_AMOUNT:
                    patients.setPatients(Long.parseLong(option.getValue()));
                    break;
                default:
                    throw new IllegalArgumentException("There is no option called '" +
                            option.getType() + "'.");
            }
        }
    }

}
