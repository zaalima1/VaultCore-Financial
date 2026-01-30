//package VaultCore_Financial.service;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.math.BigDecimal;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class TransferServiceTest {
//
//    @Autowired
//    private TransferService transferService;
//
//    @Autowired
//    private BalanceService balanceService;
//
//    @Test
//    void concurrentWithdrawTest() throws Exception {
//
//        ExecutorService executor = Executors.newFixedThreadPool(100);
//
//        for (int i = 0; i < 100; i++) {
//            executor.submit(() -> {
//                try {
//                    transferService.transfer("ACC1001", "ACC2002", new BigDecimal("10"));
//                } catch (Exception ignored) {}
//            });
//        }
//
//        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.MINUTES);
//
//        BigDecimal finalBalance = balanceService.getBalance("ACC1001");
//
//        assertTrue(finalBalance.compareTo(BigDecimal.ZERO) >= 0);
//    }
//}
