package com.smhrd.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smhrd.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	//게시글 역순으로 출력
	List<BoardEntity> findAllByOrderByIdDesc();
	
	//제목으로 검색 기능 -- containing --> like문
	List<BoardEntity> findByTitleContaining(String title);
	// select * from ~~ where title like %keyword%
	
	//내용으로 검색 기능
	// sal문 작성법은 알겠으나  JPA메소드로 만들기 힘든 경우
	// sql문 삽입하는법
	//1. @query 활용 하여 작성(특이사항 *사용이 안됨...)
	//2. 테이블명 작성시 첫글자와_글자는 대문자로 작성
	//3. :keyword --> :을 사용하면 변수로 쓰겠다.
	//4. 메소드명은 자유롭게 작성
	
	@Query("select b from BoardEntity b where b.content like %:keyword%")
	List<BoardEntity>searchContent(String keyword);
    
	
	//잓성자 기준으로 검색시능
	List<BoardEntity> findByWriter(String keyword);
	
}
