package jp.co.ecsite.dto;

import lombok.Data;

@Data
public class CategoryDTO {
	/** カテゴリID (PK) */
	private Integer categoryId;

	/** カテゴリ名 */
	private String categoryName;

}
