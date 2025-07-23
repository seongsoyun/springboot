package com.smhrd.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.board.service.BoardService;

@RestController
public class BoardRestController {

	
	@Autowired
	BoardService  boardService;
	
	@GetMapping("/board/search")
	public Object search(@RequestParam String type, @ RequestParam String keyword) {
		// 필요한 거 --> type, keyword
		
		// DB연결
		// --> service 객체에 기능 구현
		return boardService.search(type, keyword);
	}
}
