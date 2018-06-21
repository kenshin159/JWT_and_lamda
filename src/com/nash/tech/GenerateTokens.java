package com.nash.tech;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;

public class GenerateTokens {
	// Sample method to construct a JWT
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		String originalInput = "secret1";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedString);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public static void main(String[] args) {
		String id = "id";
		String issuer = "issuer";
		String subject = "subject";
		long ttlMillis = 60000;
		System.out.println(createJWT(id, issuer, subject, ttlMillis));
		System.out.println("ABC");
	}
}
