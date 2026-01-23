//package VaultCore_Financial.controller;
//
//import org.springframework.web.bind.annotation.*;
//
//import VaultCore_Financial.dto.AuthResponse;
//import VaultCore_Financial.dto.LoginRequest;
//import VaultCore_Financial.service.AuthService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody LoginRequest request) {
//        return authService.login(request);
//    }
//}
