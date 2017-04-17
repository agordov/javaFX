package javaFx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringChecker {

    private String line;
    private static final String DEFAULT_SEPARATOR = " ";

    StringChecker(String str) {
        this.line = str;
    }

    boolean containSymbols(String symbols) {
        boolean flag = line.matches(String.format(".*%s.*", symbols));
        if (flag) {
            throw new RuntimeException("Contains symbols: " + getSymbols(symbols));
        }
        return false;
    }

    private String getSymbols(String symbols) {
        Pattern p = Pattern.compile(symbols);
        Matcher m = p.matcher(line);
        String matched = "";
        while (m.find()) {
            matched += (m.group() + DEFAULT_SEPARATOR);
        }
        return matched;
    }
}
