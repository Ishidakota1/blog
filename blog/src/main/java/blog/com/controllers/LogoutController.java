package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

	// ログイン情報保持のためsessionを宣言
	@Autowired
	private HttpSession session;

	// ログアウト処理
	@GetMapping("/logout")
	public String adminLogout(Model model) {
		
		// セッションの無効化
		session.invalidate();
		model.addAttribute("result", "ログアウトしました。");
		return "redirect:/login";
	}
}
