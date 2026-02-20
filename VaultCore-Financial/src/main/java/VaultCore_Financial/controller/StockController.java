package VaultCore_Financial.controller;

import VaultCore_Financial.entity.StockPrice;
import VaultCore_Financial.service.StockApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockApiService stockApiService;

    public StockController(StockApiService stockApiService) {
        this.stockApiService = stockApiService;
    }

    @GetMapping("/{symbol}")
    public StockPrice getStock(@PathVariable String symbol) {
        return stockApiService.fetchStockPrice(symbol);
    }
}
