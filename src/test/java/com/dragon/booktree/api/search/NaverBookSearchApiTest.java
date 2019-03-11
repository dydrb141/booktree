package com.dragon.booktree.api.search;

import com.dragon.booktree.api.domain.Book;
import com.dragon.booktree.common.manager.TrustAnyTrustManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

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
	}

	public static void main(String[] args) {
		String clientId = "Un5AhjOAUi0hc5uZWkLq";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "re7BtxhYlU";//애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode("그린팩토리", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text; // json 결과
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
			URL url = new URL(apiURL);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

			con.setSSLSocketFactory(sc.getSocketFactory());

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
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