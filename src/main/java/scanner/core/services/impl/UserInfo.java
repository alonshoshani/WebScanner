package scanner.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import scanner.core.models.entities.Account;
import scanner.core.services.AccountService;

@Service
public class UserInfo {
	
	@Autowired
	private AccountService accountService;
	
	
	public Account getActiveUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       if(principal instanceof UserDetails) {
	            UserDetails details = (UserDetails)principal;
	            Account loggedIn = accountService.findByAccountName(details.getUsername());
	            return loggedIn;
	       }
	    return null;
	}
}
