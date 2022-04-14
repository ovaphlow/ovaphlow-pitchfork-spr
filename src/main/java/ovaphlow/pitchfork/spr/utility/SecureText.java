package ovaphlow.pitchfork.spr.utility;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureText {
    public static String getSecureText() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
