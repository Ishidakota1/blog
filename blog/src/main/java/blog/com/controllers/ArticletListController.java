package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Article;
import blog.com.services.ArticleService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticletListController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private ArticleService articleService;
	
	// 商品一覧画面表示
	@GetMapping("/article/list")
	public String getArticleList(Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");
		
		// admin == null ならログイン画面にリダイレクト
		// adminにデータが存在していればログインしている人の名前を商品一覧画面に渡して表示
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			List<Article> articleList = articleService.selectAllArticleList();
			model.addAttribute("userName", account.getUserName());
			model.addAttribute("articleList",articleList);
			return "blog_list.html";
		}
	}
}
