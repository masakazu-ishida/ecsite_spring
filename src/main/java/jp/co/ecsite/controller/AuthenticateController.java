package jp.co.ecsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.dto.UsersDTO;
import jp.co.ecsite.form.CartForm;
import jp.co.ecsite.form.LoginForm;
import jp.co.ecsite.service.AuthenticateService;
import jp.co.ecsite.serviceimpl.LoginSessionImpl;

@Controller
@RequestMapping("/common")
public class AuthenticateController {
	private AuthenticateService svc;
	private LoginSessionImpl sesson;

	public AuthenticateController(AuthenticateService svc, LoginSessionImpl session) {
		this.svc = svc;
		this.sesson = session;
	}

	@ModelAttribute("loginForm")
	public LoginForm setUp() {
		return new LoginForm();
	}

	@GetMapping("/login")
	public ModelAndView inedx() {
		ModelAndView mvw = new ModelAndView();

		//リダイレクトでURLが基に戻る。ここはいろんなとこから呼ばれる
		mvw.setViewName("/common/login");
		return mvw;

	}

	@PostMapping("/login")
	public ModelAndView login(LoginForm form) {
		ModelAndView mvw = new ModelAndView();

		UsersDTO user = svc.execute(form);
		if (user != null) {
			sesson.setUserDTO(user);
			if (form.getSourceUrl() == null || form.getSourceUrl().equals("")) {
				mvw.setViewName("redirect:/common/main");
			} else {

				if (form.getSourceUrl().equals("/cart/list")) {
					//カート一覧はセッションに値が入っていれば、いいので何もデータはセットしない
					mvw.setViewName("rediret:/cart/add");
				} else if (form.getSourceUrl().equals("/cart/add")) {

					CartForm cartForm = new CartForm();
					cartForm.setAmount(form.getAmount());
					cartForm.setItemId(form.getItemId());
					mvw.addObject("cartForm", cartForm);

					mvw.setViewName("forward:/cart/add");
				}
			}
		} else {
			mvw.addObject("errorMessage", "ログインID・パスワードが正しくありません");
			mvw.setViewName("/common/login");
		}
		return mvw;
	}

}
