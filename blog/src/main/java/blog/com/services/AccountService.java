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

	// アカウント検索処理
	public Account selectAccount(String accountId) {
		return accountDao.findByAccountId(accountId);
	}

	// アカウント作成処理
	public int createAccount(String accountId, String accountPassword, String accountMailadress) {

		// アカウントが既に存在していればエラーとするため100を返却
		if (accountDao.findByAccountId(accountId) != null) {
			return 100;

			// メールアドレスが既に登録されていればエラーとするため200を返却
		} else if (accountDao.findByAccountMailadress(accountMailadress) != null) {
			return 200;

			// 登録完了なら0を返却
		} else {
			// 現在日時を登録日として格納
			Timestamp registerDate = new Timestamp(System.currentTimeMillis());
			accountDao.save(new Account(accountId, accountPassword, accountMailadress, registerDate, "ユーザー"));
			return 0;
		}
	}

	// アカウント認証処理
	public Account loginCheck(String accountId, String accountPassword) {
		// アカウントIDとパスワードが一致するレコードを格納
		Account account = accountDao.findByAccountIdAndAccountPassword(accountId, accountPassword);

		// account == null ならnullを返却
		if (account == null) {
			return null;
		}
		return account;
	}
}
