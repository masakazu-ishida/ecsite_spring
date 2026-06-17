package jp.co.ecsite.serviceompl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ecsite.dao.CartDAO;
import jp.co.ecsite.dao.ItemsDAO;
import jp.co.ecsite.dao.PurchasesDAO;
import jp.co.ecsite.dao.PurchasesDetailDAO;
import jp.co.ecsite.dto.CartDTO;
import jp.co.ecsite.dto.ItemsDTO;
import jp.co.ecsite.dto.PurchasesDTO;
import jp.co.ecsite.dto.PurchasesDetailsDTO;
import jp.co.ecsite.service.PurchaseEntryCommit;
import jp.co.ecsite.util.PurchasesException;

@Service
public class PurchaseEntryCommitImpl implements PurchaseEntryCommit {

	private final PurchasesDAO purchasesDAO;
	private final PurchasesDetailDAO purchasesDetailDAO;
	private final ItemsDAO itemsDAO;
	private final CartDAO cartDAO;

	public PurchaseEntryCommitImpl(PurchasesDAO purchasesDAO, PurchasesDetailDAO purchasesDetailDAO,
			ItemsDAO itemsDAO, CartDAO cartDAO) {
		this.purchasesDAO = purchasesDAO;
		this.purchasesDetailDAO = purchasesDetailDAO;
		this.itemsDAO = itemsDAO;
		this.cartDAO = cartDAO;
	}

	public PurchasesDTO parse(String userId, String destination, List<CartDTO> cartList) {

		PurchasesDTO purchasesDTO = new PurchasesDTO();

		purchasesDTO.setDestination(destination);
		purchasesDTO.setPurchasedUser(userId);
		purchasesDTO.setPurchasedDate(LocalDate.now());
		purchasesDTO.setCancel(false);
		purchasesDTO.setDestination(null);

		List<PurchasesDetailsDTO> purchasesDetailsList = new ArrayList<PurchasesDetailsDTO>();
		for (CartDTO cart : cartList) {

			PurchasesDetailsDTO purchasesDetailDTO = new PurchasesDetailsDTO();
			purchasesDetailDTO.setAmount(cart.getAmount());
			purchasesDetailDTO.setItem(cart.getItem());
			purchasesDetailDTO.setItemId(cart.getItem().getItemId());

			purchasesDetailsList.add(purchasesDetailDTO);

		}

		purchasesDTO.setPurchaseDatails(purchasesDetailsList);
		return purchasesDTO;

	}

	@Override
	@Transactional
	public PurchasesDTO commit(String userId, String payment, String destination)
			throws PurchasesException {

		List<CartDTO> cartList = cartDAO.findByUserId(userId);

		PurchasesDTO purchasesDTO = parse(userId, destination, cartList);

		//注文を登録
		if (purchasesDAO.insert(purchasesDTO) != 1) {
			throw new PurchasesException("PurchasesDTO登録に失敗");
		} else {
			//採番した主キーを設定
			int purchasesId = purchasesDTO.getPurchaseId();

			for (PurchasesDetailsDTO purchasesDetailsDTO : purchasesDTO.getPurchaseDatails()) {
				purchasesDetailsDTO.setPurchaseId(purchasesId);

				//注文明細を登録
				if (purchasesDetailDAO.insert(purchasesDetailsDTO) != 1) {
					throw new PurchasesException("PurchasesDetailsDTO登録に失敗");
				}

				//在庫引き当て
				ItemsDTO item = purchasesDetailsDTO.getItem();
				item.setStock(item.getStock() - purchasesDetailsDTO.getAmount());
				itemsDAO.update(item);
			}
		}

		//カートからの削除
		for (CartDTO dto : cartList) {
			cartDAO.delete(dto);
		}

		return purchasesDTO;
	}

}
