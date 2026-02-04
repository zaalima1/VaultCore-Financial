package VaultCore_Financial.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.Instant;

@Entity
@Table(name = "account") 
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "mobile_num", nullable = false, unique = true)
    private String mobilenum;
    @Column(name = "date_of_birth ", nullable = false, unique = true)
    private String dateofbirth;
    @Column(name = "Gender", nullable = false, unique = true)
    private String Gender;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    
    

	public Account() {
		
	}



	
	public Account(Long id, User user, String accountNumber, String mobilenum, String dateofbirth, String gender,
			Instant createdAt) {
		
		this.id = id;
		this.user = user;
		this.accountNumber = accountNumber;
		this.mobilenum = mobilenum;
		this.dateofbirth = dateofbirth;
		Gender = gender;
		this.createdAt = createdAt;
	}




	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
}
