package jp.co.ecsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.service.LoginSession;

@Controller
@RequestMapping("/purchase")

public class PurchaseListController {

	private final LoginSession session;

	public PurchaseListController(LoginSession session) {
		this.session = session;
	}

	@GetMapping("/histrory")
	public ModelAndView index() {

		ModelAndView mvw = new ModelAndView();

		return mvw;

	}

}
