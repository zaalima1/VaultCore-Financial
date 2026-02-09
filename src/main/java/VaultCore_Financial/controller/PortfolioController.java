package VaultCore_Financial.controller;

import VaultCore_Financial.entity.Portfolio;
import VaultCore_Financial.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{userId}")
    public Portfolio getPortfolio(@PathVariable String userId) {
        return portfolioService.getPortfolio(userId);
    }
}
