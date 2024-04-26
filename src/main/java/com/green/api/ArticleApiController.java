package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.service.ArticleService;

@RestController
public class ArticleApiController {
	
	@Autowired
	private ArticleService  articleService;
	
	// GET LIST : 목록조회
	@GetMapping("/Api/Articles")
	public List<Article> index() {
		return  articleService.index();
	}

	// GET ID   : ID 로 조회
	@GetMapping("/Api/Articles/{id}")
	public Article show(@PathVariable Long id) {
		Article article = articleService.show(id);
		return  article;
	} 
	

	// POST     : INSERT - create
	// 결과     : 저장된 article 객체, 상태코드 < - 저장되었습니다
	// Generic  : 파라미터 type 을 객체 (T) type 을 사용해라 
	// <T>      : class type, <?>   T 는 외부에 입력된 type
	// ResponseEntity<Article>
	//  = Article Data + http state code : 200
	// {"id":12, "title":"새글", "content":"새글내용"}
	// HttpStatus.OK          : 200
	// HttpStatus.BAD_REQUEST : 400
	// .build() == .body(null)
	// @RequestBody : 넘어오는 값 json	
	@PostMapping("/Api/Articles")	
	public ResponseEntity<Article> create(
		    @RequestBody	ArticleForm dto
			) {
		Article created = articleService.create(dto);
		ResponseEntity<Article> resulut
		   = ( created != null) 
		   ? ResponseEntity.status(HttpStatus.OK).body(created)
		   : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				   
		return resulut;		
	}
	
	// PATCH    : UPDATE

	// DELETE   : DELETE
	
}





