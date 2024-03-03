package bytebrewers.bitpod.repository;

import bytebrewers.bitpod.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {

    Optional<Bank> findByName(String name);

}
