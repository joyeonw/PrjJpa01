package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentDto;

// 댓글 조회, 댓글 추가, 수정, 삭제
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
		
		// ResponseEntity : status.ok + dtos(arrayList->json 으로 출력) 를 리턴
		// -> json 으로 출력 (이유: @RestController 라서)를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	// 2. 댓글 생성(POST)
	// POST http://localhost:9090/Api/Articles/{articleId}/comments
//	   입력 data : {
//	   "articleId" : 4
//	   ,"nickname" : "Min"
//	   ,"body" :  "아이언맨5"
//	 }
	// 결과  
	//{
	//	"id": null,
	//	"articleId": 4,
	//	"nickname": "Min",
	//	"body": "아이언맨5"
	//	}
	// 에러 입력 : 
	// 결과 
	@PostMapping("/Api/Articles/{articleId}/comments")
	public ResponseEntity<CommentDto> create(
		@PathVariable Long articleId,     // {articleId} : 게시글 번호
		@RequestBody CommentDto dto       // 입력된 자료들 input, select
		) {
		CommentDto createdDto = commentService.create(articleId, dto);
		// 결과 응답
		
		return ResponseEntity.status(HttpStatus.OK).body( dto );
	}
/*
	
	 // 3. 댓글 수정(Patch)
	 Patch http://localhost:9090/Api/comments/7
	 수정 전 데이터 {   "article_id":6, 
 						"id":7,
			 		 	"body":"조깅",
			 		 	"nickname":"Park"	}
    수정 후 데이터 {    "article_id":6, 
						"id":7,
				 	 	"body":"수영",
				 	 	"nickname":"Park2"	}
 */
	@PatchMapping("/Api/comments/{id}")
	public ResponseEntity<CommentDto> update(
			@PathVariable Long id,
			@RequestBody CommentDto dto // 수정할 데이터를 가지고 있다
			) {
		CommentDto updatedDto = commentService.update(id, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}
	
	
	// 4. 댓글 삭제(Delete)
	// Delete  http://localhost:9090/Api/comments/7
	@DeleteMapping("/Api/comments/{id}")
	public ResponseEntity<CommentDto> delete(
			@PathVariable Long id
			) {
		CommentDto deletedDto = commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}
	
}
