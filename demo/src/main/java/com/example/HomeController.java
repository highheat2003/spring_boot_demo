package com.example;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.LoginUserDetails;
import com.example.mapper.DemoMapper;
import com.example.service.AccountService;

@Controller
public class HomeController {

	@Autowired
	private DemoMapper demoMapper;

	@Autowired
	private AccountService accountService;

	@RequestMapping("/test")
	public String test(Model model) throws Exception {
		model.addAttribute("msg", "Hello World!");

		demoMapper.set("TEST_DATA_xml");
		return "test";
	}

	
	@RequestMapping(path = "/", produces = "text/html")
	public String index() throws Exception {

		return "main";
	}

	@RequestMapping("/login")
	String login() {
		// 타임리프의 뷰.
		// 타임리프의 뷰는 기본값으로 resources/templates/<경로>.html 이다.
		// 즉 이렇게하면 resources/templates/login.html 이 불린다.
		return "login";
	}

	@ResponseBody
	@RequestMapping(path = "/mypage", produces = "text/html")
	public String mypage(@AuthenticationPrincipal LoginUserDetails userDetails) {
		return "is mypage !!<br/>" + userDetails.getAc() + " : " + userDetails.getUsername() + "<br/><a href=\"/logout\">logout</a>";
	}

	@RequestMapping("/create")
	String create() {
		return "create";
	}

	@RequestMapping("/createProcessing")
	String createProcessing(@Param("ac") String ac, @Param("pw") String pw) {
		if (accountService.create(ac, pw)) {
			return "redirect:/login";
		} else {
			return "redirect:/create?error";
		}
	}
}
