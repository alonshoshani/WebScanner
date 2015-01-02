package scanner.core.services;

import scanner.core.models.entities.Account;
import scanner.core.services.util.AccountList;



import java.util.List;

/**
 * Created by Chris on 6/28/14.
 */
public interface AccountService {
    public Account findAccount(String id);
    public Account createAccount(Account data);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
}
