package VaultCore_Financial.entity;

import java.util.Map;

public class Portfolio {

    private String userId;
    private Map<String, Integer> holdings;

    public Portfolio() {
    }

    public Portfolio(String userId, Map<String, Integer> holdings) {
        this.userId = userId;
        this.holdings = holdings;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }
}
