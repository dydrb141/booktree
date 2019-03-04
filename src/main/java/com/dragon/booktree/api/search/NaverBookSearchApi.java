package com.dragon.booktree.api.search;

import com.dragon.booktree.api.domain.Book;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class NaverBookSearchApi {
	@Autowired
	private RestTemplate restTemplate;

	@Value("naver.book.api.client.id")
	private String naverBookApiClientId;

	@Value("naver.book.api.client.secret")
	private String naverBookApiSecret;

	@Value("naver.book.api.url")
	private String naverBookApiUrl;


	public List<Book> searchBooks(String title) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(title)) {
			throw new IllegalArgumentException("searchBook title is Empty.");
		}

		String encodeTitle = URLEncoder.encode(title, "UTF-8");

		ResponseEntity<List<Book>> responseBooks = restTemplate
				.exchange(naverBookApiUrl + encodeTitle, HttpMethod.GET, new HttpEntity(getNaverApiHttpHeader()), new ParameterizedTypeReference<List<Book>>() {
				});

		if (!HttpStatus.valueOf(responseBooks.getStatusCode().name()).is2xxSuccessful()) {
			//todo:// controller advie를 통해 Expceiton 처리
		}

		return responseBooks.getBody();

	}

	private HttpHeaders getNaverApiHttpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-Naver-Client-Id", naverBookApiClientId);
		httpHeaders.set("X-Naver-Client-Secret", naverBookApiSecret);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return httpHeaders;
	}

	public static void main(String[] args) {
		String clientId = "YOUR_CLIENT_ID";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "YOUR_CLIENT_SECRET";//애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode("그린팩토리", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book.json?query="+ text; // json 결과
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
