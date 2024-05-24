package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import blog.com.models.entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

	// 保存処理と更新処理
	Comment save(Comment comment);
	
	// SELECT * FROM Products
	// 一つの記事に付随するコメントを検索
	List<Comment> findByArticleId(Long articleId);
	
	// 記事削除
	@Transactional
	void deleteByArticleId(Long articleId);
}
