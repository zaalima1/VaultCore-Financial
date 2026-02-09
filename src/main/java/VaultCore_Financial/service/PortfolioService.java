package VaultCore_Financial.service;

import VaultCore_Financial.entity.Portfolio;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PortfolioService {

    public Portfolio getPortfolio(String userId) {
        return new Portfolio(
                userId,
                Map.of(
                        "AAPL", 10,
                        "GOOGL", 5,
                        "MSFT", 8
                )
        );
    }
}
