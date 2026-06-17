package jp.co.ecsite.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PurchasesDTO {
	private int purchaseId;
	private String purchasedUser;
	private LocalDate purchasedDate;
	private String destination;
	private boolean cancel;
	private List<PurchasesDetailsDTO> purchaseDatails;
}
