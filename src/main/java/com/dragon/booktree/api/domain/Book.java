package com.dragon.booktree.api.domain;

import com.sun.tools.javac.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private int total;
	private int start;
	private int display;
	private List<BookItem> items;

}
