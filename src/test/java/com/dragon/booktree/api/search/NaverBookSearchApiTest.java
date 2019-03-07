package com.dragon.booktree.api.search;

import com.dragon.booktree.api.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NaverBookSearchApiTest {
	@Autowired
	NaverBookSearchApi naverBookSearchApi;

	@Description("네이버 book api 연동이 정상적으로 동작하는지 확인 하는 테스트")
	@Test
	public void searchBookApiTest() throws UnsupportedEncodingException {
		Book book = naverBookSearchApi.searchBook("그린팩토리");
		System.out.println(book);
	}}