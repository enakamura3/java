package teste_jwt;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtValidate {
	
	public boolean validate(String token){
		
		boolean validation = false;
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    validation = true;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
			System.out.println(exception.getMessage());
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			System.out.println(exception.getMessage());
		} finally {
			return validation;
		}		
	}
}
