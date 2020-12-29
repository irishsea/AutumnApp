package irishsea.autumnapp.security;

import java.util.Date;

import static java.lang.String.valueOf;

public class TokenManager {
    private String secretKey;

    public TokenManager(String secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken(TokenPayload payload){
        String mixedPayload = createMixedTokenPayload(payload);
        String signature = createSignature(mixedPayload);
        return String.format("%s#%s", mixedPayload, signature);
    }

    private String createMixedTokenPayload(TokenPayload payload){
        String id = String.valueOf(payload.getUserId());
        String email = payload.getEmail();
        String creationTime = String.valueOf(payload.getCreationTime().getTime());

        return String.format("%s#%s#%s", id, email, creationTime);
    }

    private String createSignature(String mixedPayload){
        return "" + mixedPayload.charAt(0) + mixedPayload.charAt(2) + secretKey.charAt(0) + secretKey.charAt(2) + secretKey.charAt(5) + mixedPayload.charAt(mixedPayload.length() - 1);
    }

    public boolean verifyToken (String token) {
        TokenPayload payload = extractPayload(token);
        String trustedToken = createToken(payload);
        return token.equals(trustedToken);
    }

    public TokenPayload extractPayload (String token) {
        String[] tokenParts = token.split("#");
        Long id = Long.valueOf(tokenParts[0]);
        String email = tokenParts[1];
        Date timeOfCreation = new Date(Long.valueOf(tokenParts[2]));

        TokenPayload payload = new TokenPayload(id, email, timeOfCreation);

        return payload;
    }
}
