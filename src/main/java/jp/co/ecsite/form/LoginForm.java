package jp.co.ecsite.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

	private String userId;
	private String password;

	private String sourceUrl;
	private int itemId;
	private int amount;

}
