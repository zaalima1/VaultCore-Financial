package VaultCore_Financial.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "otp_token")
public class OtpToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String otp;
    private Instant expiresAt;
    private boolean used;
    
	public OtpToken() {
		
	}

	public OtpToken(Long id, String email, String otp, Instant expiresAt, boolean used) {
	
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.expiresAt = expiresAt;
		this.used = used;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
    
	
   
}
