package com.dragon.booktree.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookItem {
	private String title;
	private String link;
	private String image;
	private String author;
	private int price;
	private int discount;
	private String publisher;
	private String description;
	private LocalDateTime publishDate;
}
