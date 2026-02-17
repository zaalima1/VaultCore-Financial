package VaultCore_Financial.entity;

import java.math.BigDecimal;

import org.apache.poi.hpsf.Decimal;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

   
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String accountNumber;
        
        @Column(nullable = false)
        private Double balance = 500000.0;


        private String username;
        
        @OneToOne(mappedBy = "account")
        private Admin admin;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(Double balance) {
			this.balance = balance;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Admin getAdmin() {
			return admin;
		}

		public void setAdmin(Admin admin) {
			this.admin = admin;
		}

		
		



	

  
}
