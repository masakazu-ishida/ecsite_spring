package jp.co.ecsite.dto;

import lombok.Data;

@Data
public class PurchasesDetailsDTO {

	private int purchaseDetailId;
	private int purchaseId;
	private int itemId;
	private int amount;
	private ItemsDTO item;

}
