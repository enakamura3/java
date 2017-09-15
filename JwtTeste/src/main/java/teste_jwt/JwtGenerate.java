package teste_jwt;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JwtGenerate {
	
	/***
	 * sessionDuration in miliseconds
	 * @param sessionDuration
	 * @return
	 */
	public String generate(long sessionDuration){
		
		String token = "";
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Long expiresAt = calendar.getTimeInMillis() + sessionDuration;
		
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			token = JWT.create()
//					.withClaim("sub", "users/TzMUocMF4p")
//					.withClaim("name", "Kakaroto")
//					.withClaim("scope", "self groups/admins")
					.withExpiresAt(new Date(expiresAt))
//					.withIssuer("issue")
					.sign(algorithm);
		} catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
			System.out.println(exception.getMessage());
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			System.out.println(exception.getMessage());
		}
		
		return token;
	}
}
