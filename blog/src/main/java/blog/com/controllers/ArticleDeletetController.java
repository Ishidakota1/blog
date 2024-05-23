package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.ArticleService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleDeletetController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private ArticleService articleService;

	// 商品削除処理
	@PostMapping("/article/delete")
	public String productDelete(Long articleId, Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// admin == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			// adminにデータが存在していれば商品を削除
			// 削除完了なら商品一覧画面にリダイレクト
			// 削除失敗なら商品登録画面にとどまる
			if (articleService.deleteArticle(articleId)) {
				return "redirect:/article/list";
			}
			return "redirect:/product/edit" + articleId;
		}
	}
}
