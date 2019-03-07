package com.dragon.booktree.api.search;

import com.dragon.booktree.api.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

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

	public Book searchBook(String title) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(title)) {
			throw new IllegalArgumentException("searchBook title is Empty.");
		}


		HttpEntity requestEntity = new HttpEntity(getNaverApiHttpHeader());
		ResponseEntity<Book> responseBook = restTemplate
				.exchange(naverBookApiUrl + title, HttpMethod.GET, requestEntity, Book.class);

		if (!HttpStatus.valueOf(responseBook.getStatusCode().name()).is2xxSuccessful()) {
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



	public static void main(String[] args) {
		String clientId = "Un5AhjOAUi0hc5uZWkLq";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "re7BtxhYlU";//애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode("그린팩토리", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book.json?query="+ text; // json 결과
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
			URL url = new URL(apiURL);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());

			con.setSSLSocketFactory(sc.getSocketFactory());

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
