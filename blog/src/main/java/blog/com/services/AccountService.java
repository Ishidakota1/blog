package blog.com.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	public int createAccount(String accountId, String accountPassword, String accountMailadress) {

		if (accountDao.findByAccountId(accountId) != null) {
			return 100;

		} else if (accountDao.findByAccountMailadress(accountMailadress) != null) {
			return 200;

		} else {
			Timestamp registerDate = new Timestamp(System.currentTimeMillis());
			accountDao.save(new Account(accountId, accountPassword, accountMailadress, registerDate, "ユーザー"));
			return 0;
		}
	}

	public Account loginCheck(String accountId, String accountPassword) {
		Account account = accountDao.findByAccountIdAndAccountPassword(accountId, accountPassword);
		if (account == null) {
			return null;
		}
		return account;
	}
}
