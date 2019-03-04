package com.dragon.booktree.api.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class NaverBookSearchApiTest {
	@Autowired
	NaverBookSearchApi naverBookSearchApi;

	@Description("네이버 book api 연동이 정상적으로 동작하는지 확인 하는 테스트")
	public void searchBookApiTest() throws UnsupportedEncodingException {
		naverBookSearchApi.searchBooks("그린팩토리");


	}

}