package com.nash.tech;

import java.util.Base64;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class VerifyTokens {

	// Sample method to validate and read the JWT
	public static void parseJWT(String jwt) {

		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		String originalInput = "secret1";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedString);

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(encodedString))
				.parseClaimsJws(jwt).getBody();

		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}

	public static void main(String[] args) {
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsImlhdCI6MTUxMTk0OTU3Mywic3ViIjoic3ViamVjdCIsImlzcyI6Imlzc3VlciIsImV4cCI6MTUxMTk0OTYzM30.yfCOCwkwzT2dcuMAuU4Z4tqqvvy21b85D7uYpEk0KDI";

		parseJWT(jwt);
	}
}
