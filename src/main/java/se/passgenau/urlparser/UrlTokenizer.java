package se.passgenau.urlparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author andreaskaltenbach
 */
public class UrlTokenizer {

    private static final Pattern URL_PATTERN = Pattern.compile("(.*)://(.*)");

    private String url;
    private ParseResult parseResult;

    public UrlTokenizer(String url) {
        this.url = url;
        this.parseResult = new ParseResult();
    }

    public ParseResult parse() {

        // scheme :// username : password @ hostname / path ? arg=value # anchor

        Matcher matcher = URL_PATTERN.matcher(url);

        if (matcher.find()) {
            parseResult.scheme = matcher.group(1);
            parse(matcher.group(2));
        }
        return parseResult;
    }

    private void parse(String url) {

        String[] urlParts = url.split("@");

        if (urlParts.length == 1) {
            // no authentication part available, proceed with host
            parseHost(urlParts[0]);
        }

        if (urlParts.length == 2) {
            // authentication part available, parse this separately
            parseAuthenticationPart(urlParts[0]);
            parseHost(urlParts[1]);
        }

    }

    private void parseHost(String url) {
        String[] urlParts = null;

        if (url.contains("/")) {
            urlParts = url.split("/");
            parsePath(urlParts[1]);
        } else if (url.contains("?")) {
            urlParts = url.split("\\?");
            parseQueryString(urlParts[1]);
        } else if (url.contains("#")) {
            urlParts = url.split("#");
            parseResult.fragment = urlParts[1];
        }

        parseResult.host = (urlParts == null) ? url : urlParts[0];
    }

    private void parsePath(String url) {
        String[] urlParts = url.split("\\?");

        parseResult.path = urlParts[0];

        if (urlParts.length == 2) {
            parseQueryString(urlParts[1]);
        }
    }

    private void parseQueryString(String url) {
        String[] urlParts = url.split("#");

        parseResult.query = urlParts[0];

        if (urlParts.length == 2) {
            parseResult.fragment = urlParts[1];
        }
    }

    private void parseAuthenticationPart(String url) {
        String[] urlParts = url.split(":");

        if (urlParts.length == 1) {
            parseResult.username = urlParts[0];
        }
        if (urlParts.length == 2) {
            parseResult.username = urlParts[0];
            parseResult.password = urlParts[1];
        }
    }
}
