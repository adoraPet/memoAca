package world.adorapet.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import world.adorapet.memo.common.EncryptService;
import world.adorapet.memo.user.dao.UserDAO;
import world.adorapet.memo.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(
			String loginId
			, String password
			, String email) {
		
		
		String encryptPassword = EncryptService.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, email);
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = EncryptService.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
		
	}
	
	public boolean isDuplicateLoginId(String loginId) {
		
		int count = userDAO.selectCountLoginId(loginId);
		
		if(count == 0) {  // 중복되지 않음
			return false;
		} else { // 중복됨
			return true;
		}
		
	}

}
