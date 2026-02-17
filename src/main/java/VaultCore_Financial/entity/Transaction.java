package VaultCore_Financial.entity;



import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 

	    private String txnId;
	    private String fromAccount;
	    private String toAccount;
	    private Double amount;

	    private LocalDateTime timestamp;   // 👈 MUST EXIST

	    private String status;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public String getTxnId() {
			return txnId;
		}

		public void setTxnId(String txnId) {
			this.txnId = txnId;
		}

		public String getFromAccount() {
			return fromAccount;
		}

		public void setFromAccount(String fromAccount) {
			this.fromAccount = fromAccount;
		}

		public String getToAccount() {
			return toAccount;
		}

		public void setToAccount(String toAccount) {
			this.toAccount = toAccount;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		

}
