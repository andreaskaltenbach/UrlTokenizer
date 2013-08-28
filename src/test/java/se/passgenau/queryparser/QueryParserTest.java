package se.passgenau.queryparser;

import java.util.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author andreaskaltenbach
 */
public class QueryParserTest {

    private static final String QUERY_STRING = "key1=value1&key2=value2";

    @Test
    public void testQueryParser() {
        Map<String, String> result = QueryParser.parse(QUERY_STRING);

        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }
}
