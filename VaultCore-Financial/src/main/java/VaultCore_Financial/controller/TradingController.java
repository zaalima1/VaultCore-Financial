package VaultCore_Financial.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TradingController {

	@GetMapping("/trading")
	public String tradingPage(Model model) {

	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

	    model.addAttribute("email", email);

	    return "trading"; // trading.html
	}

}
