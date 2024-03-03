package bytebrewers.bitpod.service.impl;

import bytebrewers.bitpod.entity.Bank;
import bytebrewers.bitpod.repository.BankRepository;
import bytebrewers.bitpod.utils.dto.request.bank.BankDTO;
import bytebrewers.bitpod.utils.specification.GeneralSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ExtendWith(MockitoExtension.class)
class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;
    @InjectMocks
    private BankServiceImpl bankService;
    private Bank bank;


    @BeforeEach
    public void setUp() {
        bank = new Bank();
        bank.setName("Test Bank");
        bank.setAddress("Test Address");
    }

    @DisplayName("JUnit test for save Bank")
    @Test
    public void givenBankObject_whenSave_thenReturnSavedBank() {
        //given
        BankDTO bankDTO = new BankDTO(bank.getName(), bank.getAddress());
        given(bankRepository.save(Mockito.any(Bank.class))).willReturn(bank);

        //when
        Bank saveBank = bankService.create(bankDTO);
        log.info("saved bank {}", saveBank.toString());

        //then
        assertNotNull(saveBank);
        assertEquals(saveBank.getName(), bank.getName());
        assertEquals(saveBank.getAddress(), bank.getAddress());
    }

//    @DisplayName("JUnit test for invalid save Bank")
//    @Test
//    public void givenExistingBankObject_whenSave_thenThrowException() {
//        //given
//        given(bankRepository.FindByName(Mockito.any(Bank.class))).willReturn(Optional.empty());
//        //when
//        Bank saveBank = bankRepository.save(bank);
//        //then
//        assertNotNull(saveBank);
//    }

    @DisplayName("JUnit test for get Bank")
    @Test
    public void givenBankObject_whenFindAllBank_thenReturnBank(){
        //given
        List<Bank> bankList = new ArrayList<>();
        bankList.add(bank);
        bankList.add(bank);
        bankList.add(bank);
        Specification<Bank> specification = GeneralSpecification.getSpecification(new BankDTO());
        given(bankRepository.findAll(specification, Pageable.unpaged())).willReturn(page.getPageable());

        //when
        Page<Bank> bankPage = bankService.getAll(Pageable.unpaged(), new BankDTO());

        //then
        assertNotNull(bankPage);
    }

    @DisplayName("JUnit test for get Bank by id ")
    @Test
    public void givenBankObject_whenFindById_thenReturnBank(){
        //given
        given(bankRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.of(bank));
        //when
        Bank bankServiceById = bankService.getById(bank.getId());
        log.info("this is bank service by id {}",bankServiceById);

        //then
        assertNotNull(bankServiceById);
    }

//    @DisplayName("JUnit test for update bank")
//    @Test
//    public void givenBankObject_whenUpdate_thenUpdateBank(){
//        //given
//
}