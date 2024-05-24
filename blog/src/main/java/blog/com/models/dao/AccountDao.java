package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {

	// 保存処理と更新処理
	Account save(Account account);

	// アカウント登録処理時、IDの重複チェック
	Account findByAccountId(String accountId);
	
	// アカウント登録処理時、メールアドレスの重複チェック
	Account findByAccountMailadress(String accountMailadress);

	// ログイン処理に使用。入力したログインIDとパスワードが一致するデータを取得
	Account findByAccountIdAndAccountPassword(String accountId, String accountPassword);
}
