package jp.co.ecsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.service.LoginSession;

@Controller
@RequestMapping("/member")
public class MemberChangeController {

	private final LoginSession session;

	public MemberChangeController(LoginSession session) {
		this.session = session;
	}

	@GetMapping("/update")
	public ModelAndView index() {
		ModelAndView mvw = new ModelAndView();

		mvw.addObject("user", session.getUserDTO());
		mvw.setViewName("/member/updateUser");

		return mvw;

	}

}
