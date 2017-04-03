package com.booktree;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by han on 2017-03-23.
 */
@Service
public class QuickHelpBO {
	@Autowired
	private QuickHelpMapper quickHelpMapper;

	public String getQuickHelpUrl(String url) {
		List<String> urlList = getSearchUrlList(url);

		if (urlList == null) {
			return null;
		}

		return quickHelpMapper.selectPriorityHelpUrl(urlList);
	}

	public String getQuickHelpSearchUrl(String url) {
		List<String> urlList = getSearchUrlList(url);

		if (urlList == null) {
			return null;
		}

		return quickHelpMapper.selectPrioritySearchUrl(urlList);
	}

	List<String> getSearchUrlList(String url) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		String domain = "";
		try {
			domain = new URI(url).getHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] splitUrl = domain.split("\\.");

		if (splitUrl == null) {
			return null;
		}

		List<String> urlList = new ArrayList<>();

		for (int i = 0; i < splitUrl.length; i++) {
			String quickUrl = "";

			for (int j = i; j < splitUrl.length; j++) {
				quickUrl += splitUrl[j];
				quickUrl += (splitUrl.length - 1 == j) ? "" : ".";
			}

			urlList.add(quickUrl);

		}

		return urlList;
	}

	public static void main(String[] args) {
		String url = "https://mail.daum.net/login?url=https%3A%2F%2Fmail.daum.net%2F";

		QuickHelpBO bo = new QuickHelpBO();
		bo.getSearchUrlList(url);


	}
}
