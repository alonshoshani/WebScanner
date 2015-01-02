package scanner.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import scanner.core.services.util.AccountList;
import scanner.rest.mvc.AccountController;
import scanner.rest.resources.AccountListResource;
import scanner.rest.resources.AccountResource;

import java.util.List;

/**
 * Created by Chris on 7/22/14.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
