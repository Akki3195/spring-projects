package com.transportsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/getIndex")
	private String getIndexPage() {
		return "index";
	}
}
