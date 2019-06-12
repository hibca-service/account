package bit5.team2.account.lib;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWT {
    private static final String KEY_ADMIN = "c7EmwfJWKxzOpDYygebs";
    private static final String KEY_USER = "mo65pujJOkfZ7Sy4rzB4";

    private byte[] getKey(boolean isAdmin) {
        return isAdmin ? KEY_ADMIN.getBytes() : KEY_USER.getBytes();
    }

    public String generateToken(HashMap<String,Object> data, boolean isAdmin, int duration) {
        byte[] key = getKey(isAdmin);

        try {
            JWSSigner signer = new MACSigner(key);

            JWTClaimsSet claimsSet = new JWTClaimsSet();
            claimsSet.setExpirationTimeClaim(new Date().getTime() + duration);;

            if ( ! data.isEmpty()) {
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    claimsSet.setCustomClaim(entry.getKey(),entry.getValue());
                }
            }

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Map<String,Object> getdata(String s) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(s);

            JWSVerifier verifierAdmin = new MACVerifier(this.getKey(true));
            JWSVerifier verifierUser = new MACVerifier(this.getKey(false));

            if (signedJWT.verify(verifierAdmin)) {
                if (new Date().before(new Date(signedJWT.getJWTClaimsSet().getExpirationTimeClaim()))) {
                    return signedJWT.getJWTClaimsSet().getCustomClaims();
                }
            }
            else if (signedJWT.verify(verifierUser)) {
                if (new Date().before(new Date(signedJWT.getJWTClaimsSet().getExpirationTimeClaim()))) {
                    return signedJWT.getJWTClaimsSet().getCustomClaims();
                }
            }

            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
}
