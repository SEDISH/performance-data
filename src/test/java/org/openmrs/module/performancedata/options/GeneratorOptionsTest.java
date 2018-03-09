package org.openmrs.module.performancedata.options;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.module.performancedata.options.model.ClinicOption;
import org.openmrs.module.performancedata.options.model.PatientOption;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorOptionsTest {

    @Test
    public void shouldReturnOptions() {
        String[] args = { "--clinics", "12", "--patients", "15" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(12L, generatorOptions.getClinics());
        assertEquals(15L, generatorOptions.getPatients());
    }

    @Test
    public void shouldReturnOptionsDespiteOrder() {
        String[] args = { "--patients", "15", "--clinics", "12" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(12L, generatorOptions.getClinics());
        assertEquals(15L, generatorOptions.getPatients());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnNonExistingOptions() {
        String[] args = { "--patients", "15", "--bicycle", "12" };
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(12L, generatorOptions.getClinics());
        assertEquals(15L, generatorOptions.getPatients());
    }

    @Test
    public void shouldReturnDefaultOptions() {
        String[] args = {};
        GeneratorOptions generatorOptions = new GeneratorOptions(args);

        assertEquals(ClinicOption.getDefaultClinics(), generatorOptions.getClinics());
        assertEquals(PatientOption.getDefaultPatients(), generatorOptions.getPatients());
    }

}
