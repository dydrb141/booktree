package com.dragon.booktree.search.controller;

import com.dragon.booktree.search.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class TaskController {
	@Autowired
	private TaskServiceImpl taskService;

	@GetMapping(value="/callable", produces = "text/html")
	public Callable<String> executeSlowTast() {
		Callable<String> callable = () -> taskService.execute();

		return callable;
	}
}
