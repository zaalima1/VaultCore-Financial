package VaultCore_Financial.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ledger_entry")
@Builder
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "entry_type", nullable = false)
    private String entryType; // DEBIT / CREDIT

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String reference;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    
    

	public LedgerEntry() {
		
	}

	public LedgerEntry(Long id, Account account, String entryType, BigDecimal amount, String reference,
			Instant createdAt) {
		
		this.id = id;
		this.account = account;
		this.entryType = entryType;
		this.amount = amount;
		this.reference = reference;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
