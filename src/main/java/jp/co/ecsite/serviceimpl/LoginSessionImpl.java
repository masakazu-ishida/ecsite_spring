package jp.co.ecsite.serviceimpl;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.ecsite.dto.UsersDTO;
import lombok.Data;

@Component
@SessionScope
@Data
public class LoginSessionImpl {
	private static final long serialVersionUID = 1L;
	private UsersDTO userDTO;

}
