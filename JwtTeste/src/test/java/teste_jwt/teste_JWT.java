package teste_jwt;

import org.junit.Assert;
import org.junit.Test;

public class teste_JWT {
	
	@Test
	public void validate_true(){
		
		JwtGenerate jwtGenerate = new JwtGenerate();
		JwtValidate jwtValidate = new JwtValidate();
				
		// gera um token com validade de 2 segundos
		String token = jwtGenerate.generate(2000);
		
		Assert.assertTrue(jwtValidate.validate(token) == true);
	}
	
	@Test
	public void validate_false(){
		
		JwtGenerate jwtGenerate = new JwtGenerate();
		JwtValidate jwtValidate = new JwtValidate();
				
		// gera um token com validade de 2 segundos
		String token = jwtGenerate.generate(2000);
		
		// faz o fluxo aguardar 3 segundos
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(jwtValidate.validate(token) == false);
	}
}
