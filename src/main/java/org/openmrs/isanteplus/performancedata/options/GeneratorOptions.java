package org.openmrs.isanteplus.performancedata.options;

import lombok.ToString;
import org.openmrs.isanteplus.performancedata.options.model.ClinicOption;
import org.openmrs.isanteplus.performancedata.options.model.DbLoginOption;
import org.openmrs.isanteplus.performancedata.options.model.DbName;
import org.openmrs.isanteplus.performancedata.options.model.DbPasswordOption;
import org.openmrs.isanteplus.performancedata.options.model.DbPortOption;
import org.openmrs.isanteplus.performancedata.options.model.DbServerOption;
import org.openmrs.isanteplus.performancedata.options.model.PatientOption;
import org.openmrs.isanteplus.performancedata.options.model.StartDateOption;
import org.openmrs.isanteplus.performancedata.options.model.VisitOption;
import org.openmrs.isanteplus.performancedata.options.service.CommandReader;
import org.openmrs.isanteplus.performancedata.options.util.CommandOption;
import org.openmrs.isanteplus.performancedata.options.util.CommandConstants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ToString
public class GeneratorOptions {

    private CommandReader commandReader;

    private ClinicOption clinics;

    private PatientOption patients;

    private DbLoginOption dbLogin;

    private DbPasswordOption dbPassword;

    private DbServerOption dbServer;

    private DbPortOption dbPort;

    private DbName dbName;

    private StartDateOption startDate;

    private VisitOption visitOption;

    public GeneratorOptions(String[] args) {
        commandReader = new CommandReader(args);
        clinics = new ClinicOption();
        patients = new PatientOption();
        dbLogin = new DbLoginOption();
        dbServer = new DbServerOption();
        dbPort = new DbPortOption();
        startDate = new StartDateOption();
        visitOption = new VisitOption();

        setOptions();
        checkObligatoryOptions();
    }

    public long getClinics() {
        return clinics.getClinics();
    }

    public long getPatients() {
        return patients.getPatients();
    }

    public String getDbLogin() {
        return dbLogin.getLogin();
    }

    public String getDbPassword() {
        return Optional.ofNullable(dbPassword.getPassword()).orElse(null);
    }

    public String getDbServer() {
        return dbServer.getServer();
    }

    public String getDbPort() {
        return dbPort.getPort();
    }

    public LocalDateTime getStartDate() {
        return startDate.getStartDate();
    }

    public String getDbName() {
        return Optional.ofNullable(dbName.getName()).orElse(null);
    }

    public long getVisits() {
        return visitOption.getVisits();
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
                case CommandConstants.DB_LOGIN:
                    dbLogin.setLogin(option.getValue());
                    break;
                case CommandConstants.DB_PASSWORD:
                    dbPassword = new DbPasswordOption(option.getValue());
                    break;
                case CommandConstants.DB_SERVER:
                    dbServer.setServer(option.getValue());
                    break;
                case CommandConstants.DB_PORT:
                    dbPort.setPort(option.getValue());
                    break;
                case CommandConstants.DB_NAME:
                    dbName = new DbName(option.getValue());
                    break;
                case CommandConstants.START_DATE:
                    startDate.setStartDate(option.getValue());
                    break;
                case CommandConstants.VISITS_AMOUNT:
                    visitOption.setVisits(Long.parseLong(option.getValue()));
                    break;
                default:
                    throw new IllegalArgumentException("There is no option called '" +
                            option.getType() + "'.");
            }
        }
    }

    private void checkObligatoryOptions() throws NullPointerException {
        if (dbPassword == null) {
            throw new NullPointerException("Lack of the database password.");
        }
        if (dbName == null) {
            throw new NullPointerException("Lack of the database name.");
        }
    }
}
