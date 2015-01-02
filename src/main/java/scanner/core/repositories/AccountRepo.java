package scanner.core.repositories;

import java.util.List;

import scanner.core.models.entities.Account;

/**
 * Created by Chris on 7/9/14.
 */
public interface AccountRepo {
    public List<Account> findAllAccounts();
    public Account findAccount(String id);
    public Account findAccountByName(String name);
    public Account createAccount(Account data);
}
