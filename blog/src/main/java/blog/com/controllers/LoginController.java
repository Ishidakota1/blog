package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	// ログイン画面表示
	@GetMapping("/login")
	public String getLoginPage() {
		return "user_login.html";
	}

	// ログイン処理
	@PostMapping("/login/process")
	public String loginProcess(@RequestParam String accountId, @RequestParam String accountPassword, Model model) {

		// logincheckメソッドを呼び出しadmin情報を変数に格納
		Account account = accountService.loginCheck(accountId, accountPassword);

		// admin == null ならログイン画面にとどまる。
		// adminにデータが存在していればsessionにログイン情報を保存し商品一覧画面にリダイレクト /product/list
		if (account == null) {
			model.addAttribute("result", "ログインに失敗しました。");
			return "user_login.html";
		} else {
			session.setAttribute("loginUserInfo", account);
			return "redirect:/article/list";
		}
	}
}
