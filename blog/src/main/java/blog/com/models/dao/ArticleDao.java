package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import blog.com.models.entity.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

	// 保存処理と更新処理
	Article save(Article article);
	
	// 記事の一覧表示
	List<Article> findAll();

	// 
	Article findByArticleName(String articleName);
	
	// 記事個別表示の際に該当レコードを検索
	Article findByArticleId(Long articleId);

	// IDを指定してレコード削除
	@Transactional
	void deleteByArticleId(Long articleId);
}
