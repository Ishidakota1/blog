package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.ArticleDao;
import blog.com.models.entity.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	// 商品一覧のチェックs
	public List<Article> selectAllArticleList() {
		// adminIdが存在するならfindAllメソッドで取得した内容を返却
		return articleDao.findAll();
	}

	// 商品の登録チャック
	// findByProductName == null なら保存処理を行いtureを返却
	// 商品が存在していればfalseを返却
	public boolean createArticle(String articleName, String articleDetail, String imageName,
			String accountId) {
		if (articleDao.findByArticleName(articleName) == null) {
			articleDao.save(new Article(articleName, articleDetail, imageName, 0, accountId));
			return true;
		} else {
			return false;
		}
	}

	// 商品編集画面表示
	// productId == null ならnullを返却
	public Article articleEditCheck(Long articleId) {
		if (articleId == null) {
			return null;
		} else {
			// null出ない場合はfindByProductIdを呼び出して商品情報を返却
			return articleDao.findByArticleId(articleId);
		}
	}

	// 商品更新処理
	// productId == null ならfalseを返却
	public boolean articleUpdate(Long articleId, String articleName, String articleDetail, String imageName,
			String accountId) {
		if (articleId == null) {
			return false;
		} else {
			// productIdが存在するならfindByProductIdを呼び出して更新前データを変数に格納
			Article article = articleDao.findByArticleId(articleId);

			// 更新前データを格納している変数に対してセッターで値を更新後、DBのレコードを更新
			article.setArticleName(articleName);
			article.setArticleDetail(articleDetail);
			article.setImageName(imageName);
			article.setAccountId(accountId);
//			article.setUpdateDate();
			articleDao.save(article);

			// コントローラーにはtrueを返却
			return true;
		}
	}

	// 商品の削除チェック
	// productId == null ならfalseを返却
	public boolean deleteArticle(Long articleId) {
		if (articleId == null) {
			return false;
		} else {
			// productIdが存在するならdeleteByProductIdを呼び出して商品を削除
			// コントローラーにtrueを返却
			articleDao.deleteByArticleId(articleId);
			return true;
		}
	}
}
