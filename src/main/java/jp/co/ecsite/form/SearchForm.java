package jp.co.ecsite.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {

	private String keyword;
	private int category = 1;
	private int start = 0;
	private int limit = 10;

}
