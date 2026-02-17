package VaultCore_Financial.service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import VaultCore_Financial.entity.Auditable;
import VaultCore_Financial.entity.Transaction;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.YearMonth;
import java.util.List;

@Service
public class PdfStatementService {
	 @Auditable(module = "PDF", action = "pdf_downloading")
	 public ByteArrayInputStream generateMonthlyStatement(String accountNumber, List<Transaction> transactions) {

		    // Filter transactions by accountNumber
		    List<Transaction> filteredTxns = transactions.stream()
		        .filter(txn -> accountNumber.equals(txn.getFromAccount()) || accountNumber.equals(txn.getToAccount()))
		        .toList(); // Java 16+ or use .collect(Collectors.toList()) for earlier versions

		    Document document = new Document();
		    ByteArrayOutputStream out = new ByteArrayOutputStream();

		    try {
		        PdfWriter.getInstance(document, out);
		        document.open();

		        // Title
		        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
		        Paragraph title = new Paragraph("Monthly Statement", titleFont);
		        title.setAlignment(Element.ALIGN_CENTER);
		        document.add(title);

		        document.add(new Paragraph(" "));
		        document.add(new Paragraph("Account Number: " + accountNumber));
		        document.add(new Paragraph("Month: " + YearMonth.now()));

		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		        String generatedDate = LocalDateTime.now().format(formatter);
		        document.add(new Paragraph("Statement Generated On: " + generatedDate));
		        document.add(new Paragraph(" "));

		        // Table
		        PdfPTable table = new PdfPTable(5);
		        table.setWidthPercentage(100);
		        table.addCell("Txn ID");
		        table.addCell("Date & Time");
		        table.addCell("From");
		        table.addCell("To");
		        table.addCell("Amount");

		        DateTimeFormatter txnFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		        for (Transaction txn : filteredTxns) {
		            table.addCell(txn.getTxnId());
		            String txnDate = (txn.getTimestamp() != null) ? txn.getTimestamp().format(txnFormatter) : "N/A";
		            table.addCell(txnDate);
		            table.addCell(txn.getFromAccount());
		            table.addCell(txn.getToAccount());
		            table.addCell("₹ " + txn.getAmount());
		        }

		        document.add(table);
		        document.close();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return new ByteArrayInputStream(out.toByteArray());
		}

    
}
