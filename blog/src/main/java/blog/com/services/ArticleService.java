package blog.com.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.ArticleDao;
import blog.com.models.entity.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	// 記事一覧取得
	public List<Article> selectAllArticleList() {
		return articleDao.findAll();
	}

	// 記事登録処理
	public void createArticle(String articleName, String articleDetail, String imageName, String accountId,
			String articleDate) {
		// 現在日時を登録日として格納
		Timestamp registerDate = new Timestamp(System.currentTimeMillis());
		articleDao.save(new Article(articleName, articleDetail, imageName, registerDate, 0, accountId, articleDate));
	}

	// 記事編集画面表示
	public Article selectOneArticle(Long articleId) {

		// articleId == null ならnullを返却
		if (articleId == null) {
			return null;
		} else {
			// 一件の記事情報を格納
			Article article = articleDao.findByArticleId(articleId);

			// 表示の際に読者カウントを+1、DBを更新
			int countUpOne = article.getDisplayCount();
			countUpOne++;
			article.setDisplayCount(countUpOne);
			articleDao.save(article);

			// 読者カウントが更新された情報を返却
			return article;
		}
	}

	// 記事更新処理
	public boolean articleUpdate(Long articleId, String articleName, String articleDetail, String imageName,
			String accountId, String articleDate) {

		// articleId == null ならfalseを返却
		if (articleId == null) {
			return false;
		} else {
			// 更新前の一件の記事情報格納
			Article article = articleDao.findByArticleId(articleId);

			// 現在日時を更新日として格納
			Timestamp updateDate = new Timestamp(System.currentTimeMillis());

			// 更新前の記事を格納している変数に対してセッターで値を更新後、DBのレコードを更新
			article.setArticleName(articleName);
			article.setArticleDetail(articleDetail);
			article.setImageName(imageName);
			article.setAccountId(accountId);
			article.setArticleDate(articleDate);
			article.setUpdateDate(updateDate);
			articleDao.save(article);

			// コントローラーにはtrueを返却
			return true;
		}
	}

	// 記事削除処理
	public boolean deleteArticle(Long articleId) {

		// articleId == null ならfalseを返却
		if (articleId == null) {
			return false;
		} else {
			// articleIdでDB検索、該当記事を削除
			// コントローラーにtrueを返却
			articleDao.deleteByArticleId(articleId);

			return true;
		}
	}
}
