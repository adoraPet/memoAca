package world.adorapet.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import world.adorapet.memo.user.bo.UserBO;
import world.adorapet.memo.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("email") String email
			) {
		
		int count = userBO.addUser(loginId, password, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
	
		return resultMap;
		
	}
	
	@PostMapping("/is_duplicate")
	@ResponseBody
	public Map<String, Boolean> isDuplicateLoginId(@RequestParam("loginId") String loginId) {
		
		// {"is_duplicate":true}
		// {"is_duplicate":false}
		
		Map<String, Boolean> result = new HashMap<>();
		
//		if(favoriteBO.isDuplicateUrl(url)) {  // 중복되었다. 
//			result.put("is_duplicate", true);
//		} else {
//			result.put("is_duplicate", false);
//		}
		
		result.put("is_duplicate", userBO.isDuplicateLoginId(loginId));
		
		return result;
		
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public Map<String, String> signin(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			, HttpServletRequest request){
		
		User user = userBO.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	

}