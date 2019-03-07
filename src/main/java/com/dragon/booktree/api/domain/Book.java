package com.dragon.booktree.api.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Book {
	private int total;
	private int start;
	private int display;
	private List<BookItem> items;
}
