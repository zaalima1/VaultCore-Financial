package VaultCore_Financial.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfStatementService {

    public byte[] generateMonthlyStatement(String userId) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("VaultCore Financial", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            // Subtitle
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Paragraph subTitle = new Paragraph("Monthly Statement", subTitleFont);
            subTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subTitle);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("User ID: " + userId));
            document.add(new Paragraph("Statement Period: February 2026"));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Holdings Summary"));

            document.add(new Paragraph("AAPL   - 10 Shares"));
            document.add(new Paragraph("GOOGL  - 5 Shares"));
            document.add(new Paragraph("MSFT   - 8 Shares"));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF statement", e);
        }

        return outputStream.toByteArray();
    }
}
