package jp.co.ecsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.form.SearchForm;

@Controller
@RequestMapping("/common")
public class HomeController {

	@ModelAttribute("searchForm")
	public SearchForm setUp() {
		return new SearchForm();
	}

	@GetMapping("/main")
	public ModelAndView index() {
		ModelAndView mvw = new ModelAndView();

		mvw.setViewName("/common/main");
		return mvw;

	}

}
