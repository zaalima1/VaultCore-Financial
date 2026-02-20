package VaultCore_Financial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
<<<<<<< HEAD

@Builder
=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    // Owner
=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

<<<<<<< HEAD
    // Banking identifiers
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    // Personal info (NOT UNIQUE)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "mobile_num")
    private String mobileNum;

    // Account data
=======
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private BigDecimal balance;   // ✅ ADD THIS

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
    @Column(nullable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
<<<<<<< HEAD
    }

    
    
    
	public Account() {
		
	}




	public Account(Long id, User user, String accountNumber, String fullName, String gender, String dateOfBirth,
			String mobileNum, Instant createdAt) {
		
		this.id = id;
		this.user = user;
		this.accountNumber = accountNumber;
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNum = mobileNum;
		this.createdAt = createdAt;
	}



	public Long getId() {
		return id;
	}

=======
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
    }

    
    public Long getId() {
		return id;
	}


>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public void setId(Long id) {
		this.id = id;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public User getUser() {
		return user;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public void setUser(User user) {
		this.user = user;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public String getAccountNumber() {
		return accountNumber;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public String getFullName() {
		return fullName;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

<<<<<<< HEAD
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb

	public Instant getCreatedAt() {
		return createdAt;
	}

<<<<<<< HEAD
=======

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


<<<<<<< HEAD


	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return getBalance();
	}





    
    
=======
	public BigDecimal getBalance() {
        return balance;
    }

    // ✅ CORRECT SETTER
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
}
