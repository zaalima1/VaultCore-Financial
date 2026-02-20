                                                        VAULTCORE FINANCIAL
                                   Secure Digital Banking & Trading Core (FinTech Simulation)
VaultCore Financial is a production-grade FinTech simulation project that replicates the core infrastructure of a Neo-Bank, including secure account management, money transfers, and simulated stock trading. The system is built with a strong focus on security, ACID compliance, concurrency control, and audit transparency, ensuring financial data integrity even under high load. It enforces a Double-Entry Ledger System with immutable transaction records and uses @Transactional(isolation = SERIALIZABLE) to prevent race conditions during concurrent operations. The application integrates Java 21 Virtual Threads for high-performance request handling and implements Spring Security with JWT authentication to protect against vulnerabilities like SQL Injection and XSS. Additionally, it features Fraud Detection Middleware with 2FA simulation, external Stock API integration, and AOP-based Audit Logging for compliance and reporting.


-> Key Highlights:

-> Double-Entry Ledger with immutable records.

-> High-concurrency transaction engine (100+ parallel thread testing).

-> Java 21 Virtual Threads for scalability.

-> JWT-based authentication & role-based security.

-> Fraud detection with interceptor/AOP.

-> Trading module with external API integration.

-> Audit logging & monthly statement generation.

-> React Portfolio Dashboard with visualization.
 
-> OWASP-aware secure architecture.

This project demonstrates real-world digital banking architecture with production-level design principles and financial-grade security standards
