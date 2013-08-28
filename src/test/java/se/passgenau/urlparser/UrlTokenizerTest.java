package se.passgenau.urlparser;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author andreaskaltenbach
 */
public class UrlTokenizerTest {

    private static final String url = "http://username:password@hostname/path?arg=value#anchor";
    private static final String MINIMUM_URL = "http://hostname";
    private static final String URL_WITH_HOST_AND_ANCHOR = "http://hostname#anchor";
    private static final String URL_WITH_HOST_AND_QUERY = "http://hostname?arg=value";
    private static final String USERNAME_ONLY_URL = "http://username@hostname/path?arg=value#anchor";

    @BeforeClass
    public static void checkUrls() {
        try {
            new URL(url);
            new URL(MINIMUM_URL);
            new URL(URL_WITH_HOST_AND_ANCHOR);
            new URL(URL_WITH_HOST_AND_QUERY);
            new URL(USERNAME_ONLY_URL);
        } catch (MalformedURLException e) {
            fail("Malformed test url");
        }
    }

    @Test
    public void testTokenizer() {

        ParseResult parseResult = new UrlTokenizer(url).parse();

        assertEquals("http", parseResult.scheme);
        assertEquals("username", parseResult.username);
        assertEquals("password", parseResult.password);
        assertEquals("hostname", parseResult.host);
        assertEquals("path", parseResult.path);
        assertEquals("arg=value", parseResult.query);
        assertEquals("anchor", parseResult.fragment);

    }

    @Test
    public void testTokenizerWithMinimumUrl() {

        ParseResult parseResult = new UrlTokenizer(MINIMUM_URL).parse();

        assertEquals("http", parseResult.scheme);
        assertEquals(null, parseResult.username);
        assertEquals(null, parseResult.password);
        assertEquals("hostname", parseResult.host);
        assertEquals(null, parseResult.path);
        assertEquals(null, parseResult.query);
        assertEquals(null, parseResult.fragment);

    }

    @Test
    public void testTokenizerWithHostAndAnchor() {

        ParseResult parseResult = new UrlTokenizer(URL_WITH_HOST_AND_ANCHOR).parse();

        assertEquals("http", parseResult.scheme);
        assertEquals(null, parseResult.username);
        assertEquals(null, parseResult.password);
        assertEquals("hostname", parseResult.host);
        assertEquals(null, parseResult.path);
        assertEquals(null, parseResult.query);
        assertEquals("anchor", parseResult.fragment);

    }

    @Test
    public void testTokenizerWithHostAndQuery() {

        ParseResult parseResult = new UrlTokenizer(URL_WITH_HOST_AND_QUERY).parse();

        assertEquals("http", parseResult.scheme);
        assertEquals(null, parseResult.username);
        assertEquals(null, parseResult.password);
        assertEquals("hostname", parseResult.host);
        assertEquals(null, parseResult.path);
        assertEquals("arg=value", parseResult.query);
        assertEquals(null, parseResult.fragment);

    }

    @Test
    public void testTokenizerWithUsernameOnlyUrl() {

        ParseResult parseResult = new UrlTokenizer(USERNAME_ONLY_URL).parse();

        assertEquals("http", parseResult.scheme);
        assertEquals("username", parseResult.username);
        assertEquals(null, parseResult.password);
        assertEquals("hostname", parseResult.host);
        assertEquals("path", parseResult.path);
        assertEquals("arg=value", parseResult.query);
        assertEquals("anchor", parseResult.fragment);

    }
}
