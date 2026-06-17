package jp.co.ecsite.dto;

import lombok.Data;

@Data
public class ItemsDTO {
	/** 商品ID (PK) */
	private Integer itemId;

	/** 商品名 */
	private String itemName;

	/** 製造元・メーカー */
	private String manufacturer;

	/** カテゴリID (FK) */
	private Integer categoryId;

	/** 色 */
	private String color;

	/** 価格 */
	private Integer price;

	/** 在庫数 */
	private Integer stock;

	/** おすすめフラグ */
	private Boolean recommended;

	private CategoryDTO category;

}
