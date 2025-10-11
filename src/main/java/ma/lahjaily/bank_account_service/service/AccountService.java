package ma.lahjaily.bank_account_service.service;


import ma.lahjaily.bank_account_service.dto.BankAccountRequestDTO;
import ma.lahjaily.bank_account_service.dto.BankAccountResponseDTO;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
