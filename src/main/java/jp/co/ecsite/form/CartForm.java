package jp.co.ecsite.form;

import lombok.Data;

@Data
public class CartForm {

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

	/** 数量 **/
	private int amount;

}
