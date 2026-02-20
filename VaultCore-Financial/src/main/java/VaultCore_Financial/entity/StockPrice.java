package VaultCore_Financial.entity;

public class StockPrice {

    private String symbol;
    private double price;
    private long timestamp;

    public StockPrice() {
    }

    public StockPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = System.currentTimeMillis();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
