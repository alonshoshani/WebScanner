package scanner.rest.mvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import scanner.core.models.entities.Account;
import scanner.core.services.AccountService;
import scanner.core.services.exceptions.AccountExistsException;
import scanner.core.services.util.AccountList;
import scanner.rest.exceptions.ConflictException;
import scanner.rest.resources.AccountListResource;
import scanner.rest.resources.AccountResource;
import scanner.rest.resources.asm.AccountListResourceAsm;
import scanner.rest.resources.asm.AccountResourceAsm;

/**
 * Created by Chris on 6/28/14.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
    
	
	private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required = false) String name, @RequestParam(value="password", required = false) String password) {
        AccountList list = null;
        if(name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            list = new AccountList(new ArrayList<Account>());
            if(account != null) {
                if(password != null) {
                    if(account.getPassword().equals(password)) {
                        list = new AccountList(Arrays.asList(account));
                    }
                } else {
                    list = new AccountList(Arrays.asList(account));
                }
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{accountId}",
                method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable int accountId
    ) {
        Account account = null;//accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/test",method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public void test() {
    	  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          if(principal instanceof UserDetails) {
              UserDetails details = (UserDetails)principal;
              System.out.println(details.getUsername());
              Account loggedIn = accountService.findByAccountName(details.getUsername());
          }
    }
    
    

    /*
    @RequestMapping(value="/{accountId}/blogs",
        method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable String accountId,
            @RequestBody BlogResource res)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails)principal;
            Account loggedIn = accountService.findByAccountName(details.getUsername());
            if(loggedIn.getId() == accountId) {
                try {
                    Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
                    BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
                    return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
                } catch(AccountDoesNotExistException exception)
                {
                    throw new NotFoundException(exception);
                } catch(BlogExistsException exception)
                {
                    throw new ConflictException(exception);
                }
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }
    */




}
