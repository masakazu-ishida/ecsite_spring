package jp.co.ecsite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.dto.UsersDTO;
import jp.co.ecsite.form.CartForm;
import jp.co.ecsite.form.LoginForm;
import jp.co.ecsite.service.CartAddService;
import jp.co.ecsite.service.CartConfirmService;
import jp.co.ecsite.service.CartDeleteService;
import jp.co.ecsite.service.CartListService;
import jp.co.ecsite.serviceimpl.LoginSessionImpl;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final CartAddService cartAddService;
	private final CartListService cartListService;
	private final CartConfirmService cartConfirmService;
	private final CartDeleteService cartDeleteService;

	private LoginSessionImpl loginSession;

	public CartController(CartAddService cartAddService, CartListService cartListService,
			CartConfirmService cartConfirmService, CartDeleteService cartDeleteService, LoginSessionImpl loginSession) {
		this.cartAddService = cartAddService;
		this.cartListService = cartListService;
		this.loginSession = loginSession;
		this.cartConfirmService = cartConfirmService;
		this.cartDeleteService = cartDeleteService;
	}

	@ModelAttribute("cartForm")
	public CartForm setupForm() {
		return new CartForm();
	}

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mvw = new ModelAndView();

		UsersDTO user = loginSession.getUserDTO();
		if (user == null) {
			LoginForm loginForm = new LoginForm();
			loginForm.setSourceUrl("/cart/list");

			//本来のログイン画面はLoginFormとフォームバインディングしている
			mvw.addObject("loginForm", loginForm);
			mvw.setViewName("/common/login");
			return mvw;
		}

		List<CartDTO> list = cartListService.execute(user.getUserId());
		mvw.addObject("list", list);

		int total = list.stream()
				.mapToInt(obj -> obj.getAmount())
				.sum();
		mvw.addObject("summary", total);
		mvw.setViewName("/cart/cartList");
		return mvw;

	}

	@PostMapping("/add")
	public ModelAndView add(CartForm form) {
		ModelAndView mvw = new ModelAndView();

		UsersDTO user = loginSession.getUserDTO();
		if (user == null) {

			LoginForm loginForm = new LoginForm();
			loginForm.setSourceUrl("/cart/add");
			loginForm.setAmount(form.getAmount());
			loginForm.setItemId(form.getItemId());

			//本来のログイン画面はLoginFormとフォームバインディングしている
			mvw.addObject("loginForm", loginForm);
			mvw.setViewName("/common/login");
			return mvw;

		} else {

			CartDTO cartDTO = new CartDTO();
			BeanUtils.copyProperties(form, cartDTO);
			cartDTO.setUserId(user.getUserId());
			cartDTO.setBookedDate(LocalDate.now());
			cartAddService.execute(cartDTO);

			mvw.setViewName("redirect:/cart/list");
			return mvw;
		}

	}

	@GetMapping("/confirm")
	public ModelAndView confirm(CartForm form) {
		ModelAndView mvw = new ModelAndView();

		UsersDTO user = loginSession.getUserDTO();
		CartDTO cart = cartConfirmService.execute(user.getUserId(), form.getItemId());
		mvw.addObject("cart", cart);

		mvw.setViewName("/cart/removeFromCartConfirm");

		return mvw;
	}

	@PostMapping("/delete")
	public ModelAndView delete(CartForm form) {
		ModelAndView mvw = new ModelAndView();

		UsersDTO user = loginSession.getUserDTO();
		CartDTO cart = cartConfirmService.execute(user.getUserId(), form.getItemId());

		cartDeleteService.execute(cart.getUserId(), cart.getItemId());
		mvw.addObject("cart", cart);

		mvw.setViewName("/cart/removeFromCartCommit");

		return mvw;
	}

}
