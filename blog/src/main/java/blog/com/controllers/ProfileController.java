package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	@Autowired
	private AccountService accountService;

	// プロフィール表示処理
	@GetMapping("/profile")
	public String getProfilepage(Model model) {

		// セッションからログイン情報を取得
		Account LoginInfo = (Account) session.getAttribute("loginUserInfo");
		
		// アカウントの最新情報を取得
		Account account = accountService.selectAccount(LoginInfo.getAccountId());
		
		// account == null ならログイン画面にリダイレクト
		if (account == null) {
			model.addAttribute("result", "再度ログインをお願いします。");
			return "redirect:/login";
		} else {
			model.addAttribute("account", account);
			model.addAttribute("userName", account.getUserName());
			return "blog_profile.html";
		}
	}
}
