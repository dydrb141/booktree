package com.dragon.booktree.api.search;

import com.dragon.booktree.api.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

@Component
public class NaverBookSearchApi {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${naver.book.api.client.id}")
	private String naverBookApiClientId;

	@Value("${naver.book.api.client.secret}")
	private String naverBookApiSecret;

	@Value("${naver.book.api.url}")
	private String naverBookApiUrl;

	public Book searchBook(String title) {
		if (StringUtils.isEmpty(title)) {
			throw new IllegalArgumentException("searchBook title is Empty.");
		}
		//todo:restTemaplate를 사용할 때 encoding안해도 되는 부분 정리
		//todo:restTemaplate에 대한 정리
		//todo:messageConveter에 대한 정리
		//todo:exceptionController에 대한 정리

		HttpEntity requestEntity = new HttpEntity(getNaverApiHttpHeader());
		ResponseEntity<Book> responseBook = restTemplate
				.exchange(naverBookApiUrl + title, HttpMethod.GET, requestEntity, Book.class);

		if (Objects.isNull(responseBook) || !HttpStatus.valueOf(responseBook.getStatusCode().name()).is2xxSuccessful()) {
			//todo:// controller advie를 통해 Expceiton 처리
		}

		return responseBook.getBody();
	}

	private HttpHeaders getNaverApiHttpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("X-Naver-Client-Id", naverBookApiClientId);
		httpHeaders.set("X-Naver-Client-Secret", naverBookApiSecret);

		return httpHeaders;
	}





}
