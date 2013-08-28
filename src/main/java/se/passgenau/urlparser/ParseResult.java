package se.passgenau.urlparser;

/**
 * @author andreaskaltenbach
 */
public class ParseResult {

    String scheme;
    String host;
    String username;
    String password;
    String path;
    String query;
    String fragment;

    @Override
    public String toString() {
        return "se.passgenau.urlparser.ParseResult{" +
                "scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                ", query='" + query + '\'' +
                ", fragment='" + fragment + '\'' +
                '}';
    }
}
