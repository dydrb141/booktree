package com.dragon.booktree.search.controller;

import com.dragon.booktree.api.domain.Book;
import com.dragon.booktree.api.search.NaverBookSearchApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class BookSearchController {
	private final NaverBookSearchApi naverBookearchApi;

	public BookSearchController(NaverBookSearchApi naverBookearchApi) {
		this.naverBookearchApi = naverBookearchApi;
	}

	@RequestMapping("form")
	public String searchForm() {
		return "new";
	}

	@RequestMapping("book")
	public Book searchBook(String title) {
		return naverBookearchApi.searchBook(title);
	}
}
