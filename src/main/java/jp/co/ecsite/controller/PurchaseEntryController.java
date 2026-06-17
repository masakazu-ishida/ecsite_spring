package jp.co.ecsite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.dto.PurchasesDTO;
import jp.co.ecsite.dto.PurchasesDetailsDTO;
import jp.co.ecsite.dto.UsersDTO;
import jp.co.ecsite.service.LoginSession;
import jp.co.ecsite.service.PurchaseEntryCommit;
import jp.co.ecsite.service.PurchaseEntryConfirm;
import jp.co.ecsite.util.PurchasesException;

@Controller
@RequestMapping("/purchases")
public class PurchaseEntryController {

	private final PurchaseEntryConfirm purchaseEntryConfirm;
	private final PurchaseEntryCommit purchaseEntryCommit;
	private final LoginSession session;

	public PurchaseEntryController(PurchaseEntryConfirm purchaseEntryConfirm, PurchaseEntryCommit purchaseEntryCommit,
			LoginSession session) {
		this.purchaseEntryConfirm = purchaseEntryConfirm;
		this.purchaseEntryCommit = purchaseEntryCommit;
		this.session = session;
	}

	@PostMapping("/confirm")
	public ModelAndView confirm() {

		ModelAndView mvw = new ModelAndView();

		UsersDTO user = session.getUserDTO();
		List<CartDTO> cartList = purchaseEntryConfirm.confirm(user.getUserId());

		mvw.addObject("cartList", cartList);
		mvw.setViewName("/purchase/purchaseConfirm");

		return mvw;
	}

	@PostMapping("/commit")
	public ModelAndView commit(String payment, String destination) {

		ModelAndView mvw = new ModelAndView();

		UsersDTO user = session.getUserDTO();
		PurchasesDTO purchasesDTO;
		try {
			purchasesDTO = purchaseEntryCommit.commit(user.getUserId(), payment, destination);

			//合計を計算
			int summary = 0;
			for (PurchasesDetailsDTO purchasesDetailDTO : purchasesDTO.getPurchaseDatails()) {
				summary += (purchasesDetailDTO.getAmount() * purchasesDetailDTO.getItem().getPrice());
			}

			mvw.addObject("summary", summary);
			mvw.addObject("purchases", purchasesDTO);
			mvw.setViewName("/purchase/purchaseConmmit");

		} catch (PurchasesException e) {

			mvw.setViewName("/common/error");
		}

		return mvw;

	}

}
