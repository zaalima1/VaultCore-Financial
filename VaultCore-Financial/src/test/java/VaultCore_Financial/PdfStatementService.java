package VaultCore_Financial;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
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

            document.add(new Paragraph("VaultCore Financial"));
            document.add(new Paragraph("Monthly Statement"));
            document.add(new Paragraph("User ID: " + userId));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("AAPL  - 10 Shares"));
            document.add(new Paragraph("GOOGL - 5 Shares"));
            document.add(new Paragraph("MSFT  - 8 Shares"));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF statement", e);
        }

        return outputStream.toByteArray();
    }
}
