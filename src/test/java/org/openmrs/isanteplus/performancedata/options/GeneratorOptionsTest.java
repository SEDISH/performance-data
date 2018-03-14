package org.openmrs.isanteplus.performancedata.options;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;
import org.openmrs.isanteplus.performancedata.options.model.ClinicOption;
import org.openmrs.isanteplus.performancedata.options.model.PatientOption;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorOptionsTest {

    @Test
    public void shouldReturnOptions() {
        String[] args = { "--clinics", "12", "--patients", "15", "--db-password", "secret123",
                "--db-name", "demo" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(12L, generatorOptions.getClinics());
        assertEquals(15L, generatorOptions.getPatients());
        assertEquals("secret123", generatorOptions.getDbPassword());
        assertEquals("demo", generatorOptions.getDbName());
    }

    @Test
    public void shouldReturnOptionsDespiteOrder() {
        String[] args = { "--patients", "15", "--clinics", "12", "--db-password", "secret123",
                "--db-name", "demo" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(12L, generatorOptions.getClinics());
        assertEquals(15L, generatorOptions.getPatients());
        assertEquals("secret123", generatorOptions.getDbPassword());
        assertEquals("demo", generatorOptions.getDbName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNonExistingOptions() {
        String[] args = { "--patients", "15", "--bicycle", "12", "--db-password", "secret123",
                "--db-name", "demo" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        // should throw before the line below
        assertEquals(12L, generatorOptions.getClinics());
    }

    @Test
    public void shouldReturnDefaultOptions() {
        String[] args = { "--db-password", "secret123", "--db-name", "demo" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        Assert.assertEquals(ClinicOption.getDefaultClinics(), generatorOptions.getClinics());
        Assert.assertEquals(PatientOption.getDefaultPatients(), generatorOptions.getPatients());
        assertEquals("secret123", generatorOptions.getDbPassword());
        assertEquals("demo", generatorOptions.getDbName());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotReturnWhenThereIsNoDbPassword() {
        String[] args = { "--patients", "15", "--db-name", "demo" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        // should throw before the line below
        assertEquals(15L, generatorOptions.getPatients());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotReturnWhenThereIsNoDbName() {
        String[] args = { "--patients", "15", "--db-password", "secret123" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        // should throw before the line below
        assertEquals(15L, generatorOptions.getPatients());
    }

}
