package blog.com.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.CommentDao;
import blog.com.models.entity.Comment;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public boolean createComment(String commentDetail, Long articleId, String accountId) {
		if (articleId == null) {
			return false;
		} else {
			Timestamp registerDate = new Timestamp(System.currentTimeMillis());
			commentDao.save(new Comment(commentDetail, registerDate, articleId, accountId));
			return true;
		}
	}

	public List<Comment> selectComenntListByArticleId(Long articleId) {
		// adminIdが存在するならfindAllメソッドで取得した内容を返却
		return commentDao.findByArticleId(articleId);
	}
	
	public boolean deleteComment(Long articleId) {
		if (articleId == null) {
			return false;
		} else {
			// productIdが存在するならdeleteByProductIdを呼び出して商品を削除
			// コントローラーにtrueを返却
			commentDao.deleteByArticleId(articleId);
			return true;
		}
	}
}
