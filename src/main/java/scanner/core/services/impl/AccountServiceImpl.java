package scanner.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scanner.core.models.entities.Account;
import scanner.core.repositories.AccountRepo;
import scanner.core.repositories.mongo.MongoAccountRepo;
import scanner.core.services.AccountService;
import scanner.core.services.exceptions.AccountExistsException;
import scanner.core.services.util.AccountList;

/**
 * Created by Chris on 7/10/14.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("AccountRepo")
    private AccountRepo mongoAccountRepo;


    @Override
    public Account findAccount(String id) {
        return mongoAccountRepo.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = mongoAccountRepo.findAccountByName(data.getName());
        if(account != null)
        {
            throw new AccountExistsException();
        }
        return mongoAccountRepo.createAccount(data);
    }

   

    /*
    @Override
    public BlogList findBlogsByAccount(String accountId) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }
    */

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(mongoAccountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return mongoAccountRepo.findAccountByName(name);
   

    }


}
