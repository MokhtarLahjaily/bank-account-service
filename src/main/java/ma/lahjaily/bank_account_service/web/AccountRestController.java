package ma.lahjaily.bank_account_service.web;

import ma.lahjaily.bank_account_service.dto.BankAccountRequestDTO;
import ma.lahjaily.bank_account_service.dto.BankAccountResponseDTO;
import ma.lahjaily.bank_account_service.entities.BankAccount;
import ma.lahjaily.bank_account_service.mappers.AccountMapper;
import ma.lahjaily.bank_account_service.repositories.BankAccountRepository;
import ma.lahjaily.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;
    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> bankAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO BankAccount(@PathVariable String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@PathVariable String id,@RequestBody BankAccount bankAccount) {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        BankAccount savedAccount = bankAccountRepository.save(account);
        return accountMapper.fromBankAccount(savedAccount);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
