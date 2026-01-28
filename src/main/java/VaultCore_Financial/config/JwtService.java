	package VaultCore_Financial.config;
	
	import io.jsonwebtoken.*;
	import io.jsonwebtoken.security.Keys;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Service;
	
	import java.security.Key;
	import java.time.Instant;
	import java.util.Date;
	
	@Service
	public class JwtService {
	
	    private final Key key;
	
	    public JwtService(@Value("${app.jwt.secret}") String secret) {
	        this.key = Keys.hmacShaKeyFor(secret.getBytes());
	    }
	
	    public String generateAccessToken(String subject, int minutes) {
	        Instant now = Instant.now();
	        return Jwts.builder()
	                .setSubject(subject)
	                .setIssuedAt(Date.from(now))
	                .setExpiration(Date.from(now.plusSeconds(minutes * 60L)))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }
	
	    public String extractSubject(String token) {
	        return parseClaims(token).getBody().getSubject();
	    }
	
	    public boolean isValid(String token) {
	        try {
	            parseClaims(token);
	            return true;
	        } catch (JwtException e) {
	            return false;
	        }
	    }
	
	    private Jws<Claims> parseClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	    }

		public String extractUsername(String token) {
			// TODO Auto-generated method stub
			return null;
		}
	}
