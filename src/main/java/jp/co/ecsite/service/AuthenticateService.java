package jp.co.ecsite.service;

import jp.co.ecsite.dto.UsersDTO;
import jp.co.ecsite.form.LoginForm;

public interface AuthenticateService {

	UsersDTO execute(LoginForm form);

}
