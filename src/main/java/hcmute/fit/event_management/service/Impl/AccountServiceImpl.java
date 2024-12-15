package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.AccountDTO;

import hcmute.fit.event_management.entity.*;
import hcmute.fit.event_management.entity.keys.AccountRoleId;
import hcmute.fit.event_management.repository.*;
import hcmute.fit.event_management.service.IAccountService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmailServiceImpl emailServiceImpl;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public Optional<Account> findById(Integer integer) {
        return accountRepository.findById(integer);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllById(Iterable<Integer> integers) {
        return accountRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        accountRepository.deleteAllById(integers);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    @Override
    public AccountDTO DTO(Account account) {
        List<String> roles = new ArrayList<>();
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        for (AccountRole accountRole : account.getListAccountRoles()) {
            roles.add(accountRole.getRole().getName());
        }
        if (account.getManager() != null) {
            accountDTO.setName(account.getManager().getName());
            accountDTO.setPhone(account.getManager().getPhone());
        }
        if (account.getEmployee() != null) {
            accountDTO.setName(account.getEmployee().getFullName());
            accountDTO.setPhone(account.getEmployee().getPhone());
        }

        accountDTO.setRoles(roles);
        return accountDTO;
    }
    @Override
    public List<AccountDTO> getAllAccountDTOs() {
        List<Account> listAccounts = findAll();
        return listAccounts.stream()
                .map(this::DTO)
                .toList();
    }
    private String generateRandomPassword(int length){
        // Danh sách ký tự
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_+-=~";
        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;

        SecureRandom random = new SecureRandom();

        // Bắt buộc chọn ít nhất một ký tự từ mỗi loại
        List<Character> passwordChars = new ArrayList<>();
        passwordChars.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        passwordChars.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Phần còn lại được chọn ngẫu nhiên từ tất cả các loại ký tự
        for (int i = 4; i < length; i++) {
            passwordChars.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Trộn ngẫu nhiên danh sách ký tự
        Collections.shuffle(passwordChars);

        // Chuyển đổi thành chuỗi
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }
    @Transactional
    @Override
    public int addOrUpdateAccount(boolean isUpdate, AccountDTO accountDTO) {
        Optional<Account> existingAccount = findbyEmail(accountDTO.getEmail());

        if (!isUpdate) {
            if (existingAccount.isPresent()) {
                logger.warn("Account creation failed: Account already exists");
                return 409;
            }
            return createAccount(accountDTO);
        }

        if (existingAccount.isEmpty()) {
            logger.warn("Account update failed: Account not found");
            return 404;
        }
        return updateAccount(existingAccount.get(), accountDTO);
    }
    @Override
    public int blockAccount(int accountID) {
        Optional<Account> existingAccount = findById(accountID);
        if (existingAccount.isEmpty()) {
            logger.warn("Account update failed: Account not found");
            return 404;
        }
        Account account = existingAccount.get();
        account.setActive(!account.isActive());
        save(account);
        return 200;
    }
    private int createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        String pw = generateRandomPassword(8);
        accountDTO.setPassword(passwordEncoder.encode(pw));
        BeanUtils.copyProperties(accountDTO, account);
        account.setActive(true);
        save(account);
        if (accountDTO.getManID() != 0) {
            createEmployee(accountDTO, account);
        }
        else {
            createManager(accountDTO, account);
        }
        createAccountRoles(accountDTO, account);
        emailServiceImpl.sendAccountEmail(account.getEmail(), account.getEmail(), pw);
        logger.info("Account created successfully");
        return 201;
    }
    private void createManager(AccountDTO accountDTO, Account account){
        Manager man = new Manager();

        man.setName(accountDTO.getName());
        man.setEmail(accountDTO.getEmail());
        man.setPhone(accountDTO.getPhone());
        man.setAccount(account);
        managerRepository.save(man);
    }
    private void createEmployee(AccountDTO accountDTO, Account account){
        Employee emp = new Employee();

        emp.setFullName(accountDTO.getName());
        emp.setPhone(accountDTO.getPhone());
        emp.setEmail(accountDTO.getEmail());
        emp.setManager(managerRepository.findById(accountDTO.getManID()).orElse(null));
        emp.setAccount(account);
        employeeRepository.save(emp);
    }
    private int updateAccount(Account existingAccount, AccountDTO accountDTO) {
        accountDTO.setAccountID(existingAccount.getAccountID());
        accountRoleRepository.deleteAllByAccount(existingAccount);

        Account updatedAccount = new Account();
        BeanUtils.copyProperties(accountDTO, updatedAccount);
        updatedAccount.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        updatedAccount.setActive(accountDTO.isActive());
        save(updatedAccount);

        createAccountRoles(accountDTO, updatedAccount);

        logger.info("Account updated successfully");
        return 200;
    }

    private void createAccountRoles(AccountDTO accountDTO, Account account) {
        List<AccountRole> accountRoles = new ArrayList<>();
        for (String role : accountDTO.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findByName(role);
            roleOptional.ifPresent(roleEntity -> {
                AccountRoleId accountRoleId = new AccountRoleId(account.getAccountID(), roleEntity.getRoleID());
                AccountRole accountRole = new AccountRole(accountRoleId, account, new Role(roleEntity.getRoleID(), roleEntity.getName()));
                accountRoles.add(accountRole);
            });
        }
        accountRoleRepository.saveAll(accountRoles);
    }

    @Override
    public Optional<Account> findbyEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}
