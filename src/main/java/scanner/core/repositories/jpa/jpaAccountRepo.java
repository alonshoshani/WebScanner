package scanner.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scanner.core.models.entities.Account;
import scanner.core.repositories.AccountRepo;


@Repository("AccountRepo")
@Transactional
public class jpaAccountRepo implements AccountRepo {

	@PersistenceContext
    private EntityManager em;
	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccount(String id) {
		return em.find(Account.class, id);
	}

	@Override
	public Account findAccountByName(String name) {
		Query query = em.createQuery("SELECT a FROM Account a WHERE name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
	}

	@Override
    public Account createAccount(Account data) {
		data.setCompany("ddd");
		data.setEmail("dd");
        em.persist(data);
        return data;
    }

}
