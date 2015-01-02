package scanner.core.repositories.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import scanner.core.models.entities.Account;
import scanner.core.repositories.AccountRepo;

@Component
public class MongoAccountRepo implements AccountRepo {
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public Account findAccount(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Account.class,"users");
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Account.class,"users");
	}

	@Override
	public Account createAccount(Account data) {
		mongoTemplate.save(data,"users");
		return data;
	}
}
