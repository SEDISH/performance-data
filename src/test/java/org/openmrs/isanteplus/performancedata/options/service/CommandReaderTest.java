package org.openmrs.isanteplus.performancedata.options.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openmrs.isanteplus.performancedata.options.util.CommandOption;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandReaderTest {

    @Test
    public void shouldReturnCommandOptionsList() {
        String[] args = { "--clinics", "159", "--patients", "357" };
        CommandReader reader = new CommandReader(args);

        List<CommandOption> result = reader.parseOptions();

        assertEquals(2, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        String[] args = { "527", "--clinics", "1457", "--patients" };
        CommandReader reader = new CommandReader(args);

        List<CommandOption> result = reader.parseOptions();

        assertEquals(2, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        String[] args = { "--clinics" };
        CommandReader reader = new CommandReader(args);

        List<CommandOption> result = reader.parseOptions();

        assertEquals(1, result.size());
    }

}
