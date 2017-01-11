package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mapper.DemoMapper;

@Controller
public class HomeController {

	@Autowired
	private DemoMapper demoMapper;

	@RequestMapping("/test")
	public String test(Model model) throws Exception {
		model.addAttribute("msg", "Hello World!");
		
		demoMapper.set("TEST_DATA_xml");
		return "test";
	}
}
