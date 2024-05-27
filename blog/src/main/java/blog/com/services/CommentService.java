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

	// コメント作成処理
	public boolean createComment(String commentDetail, Long articleId, String accountId) {
		
		// articleId == null ならfalseを返却
		if (articleId == null) {
			return false;
		} else {
			// 現在日時を登録日として格納
			Timestamp registerDate = new Timestamp(System.currentTimeMillis());
			commentDao.save(new Comment(commentDetail, registerDate, articleId, accountId));
			
			return true;
		}
	}

	// コメント表示処理
	public List<Comment> selectComenntListByArticleId(Long articleId) {
		
		// articleIdに付随するコメント群をリストで返却
		return commentDao.findByArticleId(articleId);
	}
	
	// コメント削除処理
	public boolean deleteComment(Long articleId) {
		
		// articleId == null ならfalseを返却
		if (articleId == null) {
			return false;
		} else {
			// articleIdでDB検索、一件の記事に付随するコメントを削除
			commentDao.deleteByArticleId(articleId);
			
			// コントローラーにtrueを返却
			return true;
		}
	}
}
