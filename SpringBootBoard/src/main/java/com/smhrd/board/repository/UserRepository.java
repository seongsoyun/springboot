package com.smhrd.board.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.board.entity.UserEntity;

@Repository // 어노테이션 안써도 됨 --> extends JpaRepository를 해주어서
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	// 1. 인터페이스 상속받기
	// 인터페이스 간 상속은 extends 키워드 사용
	
	// <> 제너릭 : 레퍼런스타입만 가능 --> 기본타입 8가지는 들어갈 수 없다
	// --> wrapper class (기본타입 8가지를 레퍼런스 타입으로 사용할 수 있게 하는 클래스)
	
	// T : entity(VO) 객체 --> class 파일로 구성 시 테이블 생성(VO)
	// ID : PK // entity가 가지고 있는 pk필드의 타입 (@ID가 걸려있는 필드의 타입)
	
	// 메소드의 기능 (기본 내장 되어 있는 메소드 소개)
	
	// 1. save(entity)
	// --> insert into entity명(table) values(매개변수로 넣은 entity);
	// 2. delete(매개변수)
	// --> delete from entity명 where pk명 = 매개변수
	// 3. findAll()
	// --> select * from entity명
	// 4. findByID(매개변수)
	// --> select * from entity명 where id = "매개변수"
	// 5. 커스텀 메소드
	// 예시) select * from 테이블명 where user_id=? and pw = ?
	// 규칙 : select는 find
	/*
	 *  단어 사이가 이어질 시 무조건 카멜기법
	 *  규칙 : select는 find
	 *  where 는 by
	 *  컬럼영 작성
	 *  and, or, order by 등 키워드가 있을 시 모두 작성
	 *  
	 *  findByUserIDandPW(매개변수1, 매개변수2)
	 *  
	 *  6. existsBy컬럼명(매개변수) : 데이터 유무를 알려주는 함수 
	 *   --> select count ~ limit
	 *   --> select * 테이블 명 where 컬럼 = 매개변수
	 *   
	 *   
	 * */
	// userId가 있는지 없는지
	// existsByUserId라는 함수는 없어요.
	// --> 커스텀으로 만들어야 합니다!
	boolean existsByUserId(String user_id);
	//user_id를 바탕으로 데이터가 존재하는지 유무를 알려주는 기능
	
	
	//로그인 기능
	//npe(null pointer exception)를 피하기 위해 사용
	//null이 왔을 시 프로그럄의 종료의 위험을 막기 위해 -> 값이 없을 시 empty를 return
	// 결론: 결과가 없을 때 null 이 아니라 없다를 보여주기 위한 객체
	Optional<UserEntity> findByUserIdAndPw(String id, String pw);
	
	
}
