package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import blog.com.models.entity.Account;
import blog.com.models.entity.Article;
import blog.com.models.entity.Comment;
import blog.com.services.ArticleService;
import blog.com.services.CommentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleDisplayController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	// 記事一覧画面表示
	@GetMapping("/article/list")
	public String getArticleList(Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			// 記事をリストにして一覧画面に受け渡し
			List<Article> articleList = articleService.selectAllArticleList();
			model.addAttribute("userName", account.getUserName());
			model.addAttribute("articleList", articleList);
			return "blog_list.html";
		}
	}

	// 記事個別表示
	@GetMapping("/article/{articleId}")
	public String getArticleOne(@PathVariable Long articleId, Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			model.addAttribute("userName", account.getUserName());
			
			// html側で編集ボタン分岐のためaccountIdを渡す
			model.addAttribute("accountId", account.getAccountId());
			
			// 記事情報を画面に受け渡し
			Article article = articleService.selectOneArticle(articleId);
			model.addAttribute("article", article);
			
			// コメント情報をリストにして受け渡し
			List<Comment> comment = commentService.selectComenntListByArticleId(articleId);
			model.addAttribute("comment", comment);
			return "blog_article.html";
		}
	}
}
