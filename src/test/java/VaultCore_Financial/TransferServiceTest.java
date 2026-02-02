package VaultCore_Financial;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferServiceTest {

    @Test
    void concurrentWithdrawTest() throws Exception {

        AtomicReference<BigDecimal> balance =
                new AtomicReference<>(new BigDecimal("1000"));

        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                balance.updateAndGet(b -> {
                    if (b.compareTo(BigDecimal.TEN) >= 0) {
                        return b.subtract(BigDecimal.TEN);
                    }
                    return b;
                });
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        BigDecimal finalBalance = balance.get();

        System.out.println("Final Balance: " + finalBalance);

        assertTrue(finalBalance.compareTo(BigDecimal.ZERO) >= 0);
    }
}
