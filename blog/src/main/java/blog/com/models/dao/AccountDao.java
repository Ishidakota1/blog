package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {

	// 保存処理と更新処理
	Account save(Account account);

	// SELECT * FROM admit WHERE admin_email = ?
	// 管理者の登録処理をするとき、同じメールアドレスが存在するならば登録しない
	// 1行のレコードを取得
	Account findByAccountId(String accountId);
	
	// SELECT * FROM admit WHERE admin_email = ?
	// 管理者の登録処理をするとき、同じメールアドレスが存在するならば登録しない
	// 1行のレコードを取得
	Account findByAccountMailadress(String accountMailadress);

	// SELECT * FROM admin WHERE admin_email = ? and password
	// ログイン処理に使用。入力したメールアドレスがとパスワードが一致するデータを取得
	Account findByAccountIdAndAccountPassword(String accountId, String accountPassword);
}
