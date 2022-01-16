package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

	@ColumnDefault("0")	// int이기 때문에 ''를 붙일 필요 x
	private int count;	// 조회수
	
	@ManyToOne	
	// Many = Board, One = User : 한명의 유저는 여러 개의 게시글을 쓸 수 있다는 뜻
	@JoinColumn(name = "userId")	// 실제로 table에 들어갈 foreign key의 컬럼명
	private User user;	
	// DB는 Object를 저장할 수 없기 때문에 foreign key를 사용하지만 Java는 가능
	// ORM을 사용하면 Object를 이용해 foreign key를 생성(userId)
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
