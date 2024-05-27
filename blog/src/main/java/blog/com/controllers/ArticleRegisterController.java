package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.ArticleService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleRegisterController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private ArticleService articleService;

	// 記事登録画面表示
	@GetMapping("/article/register")
	public String getArticleRegisterPage(Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			model.addAttribute("userName", account.getUserName());
			return "blog_register.html";
		}
	}

	// 記事登録処理
	@PostMapping("/article/register/process")
	public String productRegisterProcess(@RequestParam String articleName, @RequestParam String articleDetail,
			@RequestParam MultipartFile articleImage, @RequestParam String articleDate, Model model) {

		// セッションからログイン情報を取得
		Account account = (Account) session.getAttribute("loginUserInfo");

		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			String fileName = null;
			// ファイル名を取得
			if (articleImage != null) {
				fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())
						+ articleImage.getOriginalFilename();

				// ファイルを保存
				try {
					// ファイルを現在時刻+ファイル名で保存
					Files.copy(articleImage.getInputStream(),
							Path.of("src/main/resources/static/article-img/" + fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 記事登録処理
			articleService.createArticle(articleName, articleDetail, fileName, account.getAccountId(), articleDate);
			return "redirect:/article/list";

		}
	}
}
