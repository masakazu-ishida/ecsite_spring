package jp.co.ecsite.serviceimpl;

import org.springframework.stereotype.Service;

import jp.co.ecsite.dao.UsersDAO;
import jp.co.ecsite.dto.UsersDTO;
import jp.co.ecsite.form.LoginForm;
import jp.co.ecsite.service.AuthenticateService;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	private UsersDAO userDAO;

	public AuthenticateServiceImpl(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UsersDTO execute(LoginForm form) {

		UsersDTO user = userDAO.findById(form.getUserId());
		if (user != null) {
			if (!user.getPassword().equals(form.getPassword())) {
				user = null;
			}
		}
		return user;
	}

}
