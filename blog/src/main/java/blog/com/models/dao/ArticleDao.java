package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

	// 保存処理と更新処理
	Article save(Article article);
	
	// SELECT * FROM Products
	// 商品の一覧表示
	List<Article> findAll();

	// SELECT * FROM article WHERE article_id = ?
	// 管理者の登録処理をするとき、同じメールアドレスが存在するならば登録しない
	// 1行のレコードを取得
	Article findByArticleName(String articleName);
	
	Article findByArticleId(Long articleId);

	// DELETE * FROM article WHERE article_id = ? and password
	// ログイン処理に使用。入力したメールアドレスがとパスワードが一致するデータを取得
	void deleteByArticleId(Long articleId);
}
