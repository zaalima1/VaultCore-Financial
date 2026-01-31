//package VaultCore_Financial.service;
//
//
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.math.BigDecimal;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
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
//                    TransferService.transfer(
//                        "ACC1001",
//                        "ACC2002",
//                        new BigDecimal("10")
//                    );
//                } catch (Exception e) {
//                    // ignore errors
//                }
//            });
//        }
//
//        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.MINUTES);
//
//        BigDecimal finalBalance;
//		try {
//			finalBalance = BalanceService.getBalance("ACC1001");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        System.out.println("Final Balance: " + finalBalance);
//
//        assertTrue(finalBalance.compareTo(BigDecimal.ZERO) >= 0);
//    }
//}
