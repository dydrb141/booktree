package com.booktree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by han on 2017-03-23.
 */
@Controller
public class QuickHelpController {
	@Autowired
	private QuickHelpBO quickHelpBO;

	@RequestMapping("/search/help")
	@ResponseBody
	public String seachQuickHelp(String url) {
		return quickHelpBO.getQuickHelpUrl(url);
	}

	@RequestMapping("/search/search")
	@ResponseBody
	public String searchServiceNo(String url) {
		return quickHelpBO.getQuickHelpSearchUrl(url);
	}

	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("name", "hand");

		return "hello";
	}


}
