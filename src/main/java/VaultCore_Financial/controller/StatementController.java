package VaultCore_Financial.controller;

import VaultCore_Financial.service.PdfStatementService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statements")
public class StatementController {

    private final PdfStatementService pdfService;

    public StatementController(PdfStatementService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping(
        value = "/{userId}",
        produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> downloadStatement(@PathVariable String userId) {

        byte[] pdf = pdfService.generateMonthlyStatement(userId);

        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=monthly-statement-" + userId + ".pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdf.length)
                .body(pdf);
    }
}
