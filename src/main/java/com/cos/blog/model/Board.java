package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	// 대용량 데이터
	private String content;	
	// 섬머노트 라이브러리 : <html>태그가 섞여서 디자인 됨.

	private int count;	// 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)	
	// fetch = FetchType.EAGER : select할 때 무조건 데이터 값을 가져오라는 뜻
	// fetch = FetchType.LAZY : select할 때 필요할 때만 데이터 값을 가져오라는 뜻
	// Many = Board, One = User : 한명의 유저는 여러 개의 게시글을 쓸 수 있다는 뜻
	@JoinColumn(name = "userId")	// 실제로 table에 들어갈 foreign key의 컬럼명
	private User user;	
	// DB는 Object를 저장할 수 없기 때문에 foreign key를 사용하지만 Java는 가능
	// ORM을 사용하면 Object를 이용해 foreign key를 생성(userId)
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)	
	// mappedBy : 연관관계의 주인이 아니다.(FK가 아니다.) 따라서 table에 컬럼을 따로 만들지 말라는 뜻
	// board를 select할 대 Join을 해서 데이터를 얻기 위해 존재하는 객체
	// board = Reply에 있는 객체 명(private Board board)
	// cascade = CascadeType.REMOVE : board 게시글을 지울 때 관련된 reply를 모두 삭제한다.
	@JsonIgnoreProperties({"board", "user"})	// 호출된 reply에서 다시 board를 호출하지 않게 한다.(무한참조 방지)
	@OrderBy("createDate desc")
	private List<Reply> replys;	// 하나의 게시글에는 여러 개의 댓글을 포함하고 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
