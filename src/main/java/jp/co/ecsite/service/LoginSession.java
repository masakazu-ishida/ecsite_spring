package jp.co.ecsite.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.ecsite.dto.UsersDTO;
import lombok.Data;

@Component
@SessionScope
@Data
public class LoginSession {
	private static final long serialVersionUID = 1L;
	private UsersDTO userDTO;

}
