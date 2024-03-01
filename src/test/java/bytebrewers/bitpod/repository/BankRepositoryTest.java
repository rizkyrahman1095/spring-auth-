package bytebrewers.bitpod.repository;

import bytebrewers.bitpod.entity.Bank;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BankRepositoryTest {
    public BankRepository bankRepository ;


    @Test
    public void testSaveBank() {

        Bank bank = new Bank();
        bank.setName("BNI");
        bank.setAddress("Jl. Jendral Sudirman No. 1");
        bankRepository.save(bank);
        assertNotNull(bankRepository);
    }
}
