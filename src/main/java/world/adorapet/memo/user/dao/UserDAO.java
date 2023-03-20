package world.adorapet.memo.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import world.adorapet.memo.user.model.User;

@Repository
public interface UserDAO {
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("email") String email);
	
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	public int selectCountLoginId(@Param("loginId") String loginId);
	
}
