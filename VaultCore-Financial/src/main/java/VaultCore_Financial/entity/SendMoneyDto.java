package VaultCore_Financial.entity;

import java.math.BigDecimal;

public class SendMoneyDto {

    private String email;        // sender email
    private String toAccount;    // receiver account number
    private BigDecimal amount;   // money to send

    public SendMoneyDto() {
    }

    public SendMoneyDto(String email, String toAccount, BigDecimal amount) {
        this.email = email;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
