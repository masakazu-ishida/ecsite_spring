package jp.co.ecsite.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CartDTO {
	private String userId;
	private Integer itemId;
	private Integer amount;
	private LocalDate bookedDate;
	private ItemsDTO item;

}
