package VaultCore_Financial.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import VaultCore_Financial.entity.Loan;
import VaultCore_Financial.repo.AdminRepository;
import VaultCore_Financial.repo.LoanRepository;

@Controller
@RequestMapping("/user")
public class Admin_loan {
	

	@Autowired
	private LoanRepository loanRepository;
	
	
	

	    @GetMapping("/loan")
	    public String showLoanPage(Model model) {
	        model.addAttribute("loan", new Loan());
	        return "user_loan";
	    }

	    @PostMapping("/loan/apply")
	    public String applyLoan(@ModelAttribute Loan loan) {

	        loan.setStatus("PENDING");
	        loanRepository.save(loan);

	        return "redirect:/user/req_sub";
	    }

	    @GetMapping("/req_sub")
	    public String successPage() {
	        return "loan_success";
	    }
	

	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {

	    List<Loan> pendingLoans = loanRepository.findByStatus("PENDING");

	    model.addAttribute("loans", pendingLoans);  // 👈 change name

	    return "Admin-loan-management";
	}

	 @GetMapping("/admin/loans")
 	public String viewLoans(Model model) {

 	    List<Loan> loans = loanRepository.findAll();

 	    model.addAttribute("loans", loans);

 	    return "Admin-loan-management";
 	}
	 

 	@GetMapping("/admin/loan/approve/{id}")
 	public String approveLoan(@PathVariable Long id) {

 	    Loan loan = loanRepository.findById(id).get();

 	    double principal = loan.getAmount();
 	    double annualRate = loan.getInterestRate();
 	    int months = loan.getTenureMonths();

 	    double monthlyRate = annualRate / 12 / 100;

 	    double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months))
 	            / (Math.pow(1 + monthlyRate, months) - 1);

 	    loan.setEmiAmount(emi);
 	    loan.setStatus("APPROVED");
 	    loan.setStartDate(LocalDate.now());
 	    loan.setEndDate(LocalDate.now().plusMonths(months));

 	    loanRepository.save(loan);

 	    return "redirect:/user/admin/loans";
 	}
 	@GetMapping("/admin/loan/reject/{id}")
 	public String rejectLoan(@PathVariable Long id) {

 	    Loan loan = loanRepository.findById(id).get();
 	    loan.setStatus("REJECTED");

 	    loanRepository.save(loan);

 	    return "redirect:/user/admin/loans";
 	}
 
    




}
