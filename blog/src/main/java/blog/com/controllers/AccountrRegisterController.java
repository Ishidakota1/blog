package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller
public class AccountrRegisterController {

	@Autowired
	private AccountService accountService;

	// 登録画面表示
	@GetMapping("/register")
	public String getAccountRegisterPage() {
		return "user_register.html";
	}

	// 登録処理
	@PostMapping("/register/process")
	public String accountRegisterProcess(@RequestParam String accountId, @RequestParam String accountPassword,
			@RequestParam String accountMailadress, Model model) {

		int result = accountService.createAccount(accountId, accountPassword, accountMailadress);
		// 戻り値のステータスによって動作変化
		switch (result) {
		case 0:
			model.addAttribute("result", "アカウント登録が完了しました。");
			return "user_login.html";
		case 100:
			model.addAttribute("result", "ログインIDは既に登録されています。");
			return "user_register.html";
		case 200:
			model.addAttribute("result", "メールアドレスは既に登録されています。");
			return "user_register.html";
		}
		
		return null;
	}
}
