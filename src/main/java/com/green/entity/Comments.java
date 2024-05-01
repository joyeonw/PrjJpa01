package com.green.entity;

import com.green.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // 클래스를 테이블로 생성된다
@Data                     // @getter @setter @tostring hashcode, equlas
@NoArgsConstructor     	  // 기본생성자
@AllArgsConstructor       // 모든인자 생성자
@SequenceGenerator(	
		name="COMMENTS_SEQ_GENERATOR", 
		sequenceName = "COMMENTS_SEQ", 
		initialValue = 1,   // 초기값
		allocationSize = 1
					) // 증가치   
public class Comments {
	@Id                       // 기본키
	@GeneratedValue(strategy  = GenerationType.SEQUENCE,
    				generator = "COMMENTS_SEQ_GENERATOR")
	private Long id; // id
	 
	@ManyToOne                     // 관계 다대일 설정 ( Comments <-> Article )
	@JoinColumn(name="article_id") // 외래키 칼럼(부모키 article id 칼럼)
	private Article article;       // 연결될 entity 객체의 이름  
	
//	@Column(name="nick_name", nullable=false, length=255)
  // oracle 11g varchar2 최대 4000 byte  
  // oracle 12g varchar2 최대 32000 byte  
	@Column
	private String nickname; // nickname
	//@Column(name="body", nullable=false, length=255)
	@Column
	private String body;     // body
	
	public static Comments createComment(
			CommentDto dto, Article article) {
		if(dto.getId() != null) 	// 입력된 댓글에 id 가 존재하면
			throw new IllegalArgumentException(
				"댓글 생성 실패! 댓글의 id가 없어야 합니다");
		
		// dto.getArticleId() : 입력받은 게시글의 id
		// article.getId()    : 조회한   게시글의 id 
		if(dto.getArticleId() != article.getId())
			throw new IllegalArgumentException(
				"댓글 생성 실패! 게시글의 id가 잘못 되었습니다");
			
		return new Comments(
				dto.getId(),            // 입력 받은 댓글 아이디
				article,				// 조회한 부모 게시글 정보
				dto.getNickname(),      // 입력 받은 댓글 닉네임
				dto.getBody()           // 입력 받은 댓글 내용
		);
	}

	public void patch(CommentDto dto) {
		if (this.id != dto.getId() )
			throw new IllegalArgumentException(
					"댓글 수정 실패! 잘못된 아이디가 입력되었습니다"	);			
		if (dto.getNickname() != null) // 입력 받은 수정할 닉네임이 존재하면
			this.nickname = dto.getNickname();
		if (dto.getBody() != null) // 입력 받은 수정할 댓글 내용이 존재하면
			this.body = dto.getBody();
	}
}
