package VaultCore_Financial.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.*;
import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Loan;
import VaultCore_Financial.repo.AdminRepository;
import VaultCore_Financial.repo.LoanRepository;

@Controller
public class admin_view 

{
	@Autowired
	private AdminRepository adminRepository;
	private LoanRepository loanRepository;
	@GetMapping("/admin/users")
	public String viewUsers(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "") String keyword,
	        @RequestParam(defaultValue = "") String status,
	        Model model) {

	    if (page < 0) {
	        page = 0;
	    }

	    Pageable pageable = PageRequest.of(page, 5);

	    Page<Admin> userPage;

	    if (!keyword.isEmpty() && !status.isEmpty()) {
	        userPage = adminRepository
	                .findByUsernameContainingIgnoreCaseAndStatus(keyword, status, pageable);
	    } else if (!keyword.isEmpty()) {
	        userPage = adminRepository
	                .findByUsernameContainingIgnoreCase(keyword, pageable);
	    } else if (!status.isEmpty()) {
	        userPage = adminRepository
	                .findByStatus(status, pageable);
	    } else {
	        userPage = adminRepository.findAll(pageable);
	    }

	    model.addAttribute("users", userPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", userPage.getTotalPages());

	    return "user-list";
	}
	

}
