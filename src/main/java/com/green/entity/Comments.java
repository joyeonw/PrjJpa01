package com.green.entity;

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
@SequenceGenerator(	name="COMMENTS_SEQ_GENERATOR", 
					sequenceName = "COMMENTS_SEQ", 
					initialValue = 1,   // 초기값
					allocationSize = 1) // 증가치   
public class Comments {
	@Id                       // 기본키
	@GeneratedValue(strategy  = GenerationType.SEQUENCE,
    				generator = "COMMENTS_SEQ_GENERATOR")
	private Long id; // id
	 
	@ManyToOne                     // 관계 다대일 설정 ( Comments <-> Article )
	@JoinColumn(name="article_id") // 외래키 칼럼(부모키 article id 칼럼)
	private Article article;       // 연결될 entity 객체의 이름  
	
//	@Column(name="nick_name", nullable=false, length=255)
	@Column
	private String nickname; // nickname
	//@Column(name="body", nullable=false, length=255)
	@Column
	private String body;     // body
}
