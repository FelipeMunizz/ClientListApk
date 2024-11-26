package com.femuniz.clientlist.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

public class JWTUtils {
    /**
     * Método genérico para extrair uma claim de um token JWT.
     *
     * @param token       O token JWT.
     * @param claimName   O nome da claim a ser extra&iacute;da.
     * @return O valor da claim como String, ou null se n&atilde;o for encontrado.
     */
    public static String GetClaimsValue(String token, String claimName){
        try {
            return JWT.decode(token).getClaim(claimName).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
