package VaultCore_Financial.controller;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.poi.ss.usermodel.Row;
import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.repo.AdminRepository;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin")
public class export {
	@Autowired
	private AdminRepository userRepo;
	@GetMapping("/users/export")
	public void exportToExcel(HttpServletResponse response) throws Exception {

	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition",
	            "attachment; filename=users.xlsx");

	    List<Admin> users =userRepo.findAll();

	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Users");

	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("ID");
	    header.createCell(1).setCellValue("Username");
	    header.createCell(2).setCellValue("Email");
	    header.createCell(3).setCellValue("accountnumber");

	    int rowNum = 1;

	    for (Admin user : users) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(user.getId());
	        row.createCell(1).setCellValue(user.getUsername());
	        row.createCell(2).setCellValue(user.getEmail());
	        row.createCell(3).setCellValue((double) user.getStatus());
	    }

	    workbook.write(response.getOutputStream());
	    workbook.close();
	}

}
