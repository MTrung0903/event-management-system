package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.AccountDTO;
import hcmute.fit.event_management.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAccountService {


    Optional<Account> findById(Integer integer);

    List<Account> findAll();
    List<Account> findAllById(Iterable<Integer> integers);
    long count();
    void delete(Account entity);
    void deleteAll();
    void deleteAllById(Iterable<? extends Integer> integers);
    <S extends Account> S save(S entity);
    List<Account> findAll(Sort sort);
    <S extends Account> Optional<S> findOne(Example<S> example);
    AccountDTO DTO(Account account);
    List<AccountDTO> getAllAccountDTOs();

    @Transactional
    int addOrUpdateAccount(boolean isUpdate, AccountDTO accountDTO);

    int blockAccount(int accountID);

    Optional<Account> findbyEmail(String email);
}
