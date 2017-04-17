package javaFx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringChecker {

    private static String line;
    private static final String DEFAULT_SEPARATOR = " ";
    private static String SEPARATOR;

    StringChecker(String str, String separator) {
        line = str;
        SEPARATOR = separator;
    }

    StringChecker(String str) {
        new StringChecker(str, DEFAULT_SEPARATOR);
    }

    public boolean containSymbols(String symbols) {
        return line.matches(String.format(".*%s.*", symbols));
    }

    public String getSymbols(String symbols) {
        StringBuilder matched = new StringBuilder();
        Pattern p = Pattern.compile(symbols);
        Matcher m = p.matcher(line);
        while (m.find()) {
            matched.append(String.format("%s", m.group() + SEPARATOR));
        }
        return matched.toString();
    }
}
