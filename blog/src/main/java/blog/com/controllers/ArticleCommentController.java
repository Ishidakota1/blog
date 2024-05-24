package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.CommentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleCommentController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private CommentService commentService;

	// コメント登録処理
	@PostMapping("/article/commesnt/process")
	public String registerComment(@RequestParam String commentDetail,@RequestParam Long articleId,Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {

			// コメント登録処理後記事画面に遷移
			commentService.createComment(commentDetail, articleId, account.getAccountId());
			return "redirect:/article/" + articleId;
		}
	}
}
