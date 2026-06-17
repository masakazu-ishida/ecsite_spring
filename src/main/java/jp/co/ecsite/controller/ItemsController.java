package jp.co.ecsite.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.dto.ItemsDTO;
import jp.co.ecsite.form.CartForm;
import jp.co.ecsite.form.SearchForm;
import jp.co.ecsite.service.ItemsDetailService;
import jp.co.ecsite.service.ItemsSearchService;

@Controller
@RequestMapping("/items")
public class ItemsController {

	private ItemsSearchService itemSearchSVC;
	private final ItemsDetailService itemDetaiSVC;

	public ItemsController(ItemsSearchService itemSearchSVC, ItemsDetailService itemDetaiSVC) {
		this.itemSearchSVC = itemSearchSVC;
		this.itemDetaiSVC = itemDetaiSVC;
	}

	@GetMapping("/search")
	public ModelAndView search(String keyword, int category) {

		ModelAndView mvw = new ModelAndView();

		SearchForm form = new SearchForm(keyword, category, 1, 10);

		List<ItemsDTO> list = itemSearchSVC.execute(form);

		mvw.setViewName("/items/searchResult");
		mvw.addObject("list", list);
		return mvw;
	}

	@ModelAttribute("cartForm")
	CartForm setupForm() {
		return new CartForm();
	}

	@GetMapping("/detail")
	public ModelAndView index(int itemId, @ModelAttribute("cartForm") CartForm cartForm) {
		ModelAndView mvw = new ModelAndView();
		ItemsDTO itemDTO = itemDetaiSVC.execute(itemId);
		BeanUtils.copyProperties(itemDTO, cartForm);

		mvw.addObject("item", cartForm);
		mvw.setViewName("/items/itemDetail");
		return mvw;
	}

}
