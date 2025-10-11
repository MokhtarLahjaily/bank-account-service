package ma.lahjaily.bank_account_service;

import ma.lahjaily.bank_account_service.dto.BankAccountRequestDTO;
import ma.lahjaily.bank_account_service.entities.BankAccount;
import ma.lahjaily.bank_account_service.enums.AccountType;
import ma.lahjaily.bank_account_service.mappers.AccountMapper;
import ma.lahjaily.bank_account_service.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				BankAccountRequestDTO requestDTO = new BankAccountRequestDTO(
						10000 + Math.random() * 90000,
						"MAD",
						Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT
				);
				BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(requestDTO);
				bankAccountRepository.save(bankAccount);
			}
		};
	}
}
