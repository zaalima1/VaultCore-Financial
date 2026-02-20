package VaultCore_Financial.controller;


import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import VaultCore_Financial.entity.Transaction;
import VaultCore_Financial.repo.TransactionRepository;
import VaultCore_Financial.service.PdfStatementService;
import VaultCore_Financial.service.TransactionService;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/statement")
public class StatementController {

    private final TransactionRepository transactionRepository;
    private final PdfStatementService pdfService;
    private final TransactionService txnService;

  
        

    public StatementController(TransactionRepository transactionRepository,
                               PdfStatementService pdfService,TransactionService txnService) {
        this.transactionRepository = transactionRepository;
        this.pdfService = pdfService;
        this.txnService = txnService;
    }

    // ✅ Show UI page
    @GetMapping("/page")
    public String showStatementPage() {
        return "statement";
    }

    @GetMapping("/download/{accountNumber}")
    public ResponseEntity<byte[]> downloadStatement(@PathVariable String accountNumber) throws Exception {

        List<Transaction> transactions =
                txnService.getTransactionsByAccount(accountNumber);

        ByteArrayInputStream pdf =
                pdfService.generateMonthlyStatement(accountNumber, transactions);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "attachment; filename=statement_" + accountNumber + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

}
