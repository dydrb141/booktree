package com.dragon.booktree.search.service;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl {

	public String execute() {
		try {
			Thread.sleep(50000);
			return "Task finished";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "Fail";
		}
	}
}
