package org.openmrs.isanteplus.performancedata.options.service;

import org.openmrs.isanteplus.performancedata.options.util.CommandOption;
import org.openmrs.isanteplus.performancedata.options.util.CommandConstants;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {

    private List<String> args;
    private Pattern pattern;

    public CommandReader(String[] args) {
        this.args = Arrays.asList(args);
        this.pattern = Pattern.compile("(^" + CommandConstants.PREFIX + ")([^\\s]*)");
    }

    public List<CommandOption> parseOptions() {
        List<CommandOption> result = new LinkedList<>();
        Iterator it = args.iterator();

        while(it.hasNext()) {
            String value;
            String type = parseOptionType((String)it.next());

            if (it.hasNext()) {
                value = (String)it.next();
            } else {
                throw new NullPointerException();
            }
            result.add(new CommandOption(type, value));
        }

        return result;
    }

    private String parseOptionType(String type) {
        String result;
        Matcher matcher = pattern.matcher(type);

        if (matcher.find()) {
            result = matcher.group(2);
        } else {
            throw new IllegalArgumentException("There is no option called '" + type + "'.");
        }

        return result;
    }
}
