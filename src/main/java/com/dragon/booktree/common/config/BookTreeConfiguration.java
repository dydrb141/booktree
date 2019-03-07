package com.dragon.booktree.common.config;

import com.dragon.booktree.api.search.TrustAnyTrustManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class BookTreeConfiguration {
	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException {
		return  new RestTemplate(buildCustomRequestFactory());
	}

	private HttpComponentsClientHttpRequestFactory buildCustomRequestFactory() throws NoSuchAlgorithmException, KeyManagementException {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sc);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

		requestFactory.setHttpClient(httpClient);

		return requestFactory;
	}
}
