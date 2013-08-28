package se.passgenau.queryparser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andreaskaltenbach
 */
public class QueryParser {
    
    public static final Map<String, String> parse(String queryString) {
        Map<String, String> result = new HashMap<String, String>();

        for (String argument : queryString.split("\\&")) {
            String[] parts = argument.split("=");
            result.put(parts[0], parts[1]);
        }
        
        return result;
    }
}
