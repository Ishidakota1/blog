package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.ArticleService;
import blog.com.services.CommentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleDeletetController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	// 記事削除処理
	@PostMapping("/article/delete")
	public String productDelete(@RequestParam Long articleId, Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			//　記事に付帯するコメントも同時に削除
			// 削除後は記事一覧に遷移
			commentService.deleteComment(articleId);
			articleService.deleteArticle(articleId);

			return "redirect:/article/list";
		}
	}
}
