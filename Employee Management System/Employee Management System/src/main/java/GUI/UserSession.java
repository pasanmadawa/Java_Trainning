package GUI;

public class UserSession {
    private static String jwtToken;

    private UserSession() {}

    public static void setJwtToken(String token) {
        jwtToken = token;
    }

    public static String getJwtToken() {
        return jwtToken;
    }
}
