package VaultCore_Financial.service;

import VaultCore_Financial.entity.StockPrice;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StockApiService {

    private final Random random = new Random();

    public StockPrice fetchStockPrice(String symbol) {

        long start = System.currentTimeMillis();

        try {
            Thread.sleep(50 + random.nextInt(100)); // mock latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long latency = System.currentTimeMillis() - start;
        if (latency > 300) {
            throw new RuntimeException("Latency exceeded 300ms");
        }

        double price = 100 + random.nextDouble() * 50;
        return new StockPrice(symbol, price);
    }
}
