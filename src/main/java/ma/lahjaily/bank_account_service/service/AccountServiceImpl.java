package ma.lahjaily.bank_account_service.service;

import ma.lahjaily.bank_account_service.dto.BankAccountRequestDTO;
import ma.lahjaily.bank_account_service.dto.BankAccountResponseDTO;
import ma.lahjaily.bank_account_service.entities.BankAccount;
import ma.lahjaily.bank_account_service.mappers.AccountMapper;
import ma.lahjaily.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
