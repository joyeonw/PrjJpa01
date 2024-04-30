package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentDto;
import com.green.entity.Comments;


@RestController
public class CommentApiController {
	
	@Autowired
	private CommentService commentService; 
	
	// 1. 댓글 조회(GET)
	@GetMapping("/Api/Articles/{articleId}/comments")
	public ResponseEntity<List<CommentDto>> comments(
		@PathVariable Long articleId) {
		
		// 정보 조회를 서비스에게 위임
		List<CommentDto> dtos = commentService.comments(articleId);
		
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
//	// 2. 댓글 생성(POST)
//	@PostMapping("/Api/Articles/{articleId}/comments")
//	/*
//	public ResponseEntity<List<Comments>> create(
//		@PathVariable Long articleId) {
//			
//	}*/
//	
//	// 3. 댓글 수정(Patch)
//	@PatchMapping("/Api/comments/{id}")
//	
//	// 4. 댓글 삭제(Delete)
//	@DeleteMapping("/Api/comments/{id}")
}
