package com.oracle.oBootDBConnect.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.oracle.oBootDBConnect.domain.Member;

public class jdbcTemplateMemberRepository implements MemberRepository {
	
	private final JdbcTemplate jdbcTemplate;
	public jdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public Member save(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", member.getId());
		parameters.put("name", member.getName());
		jdbcInsert.execute(parameters);
		
		return member;
	}


	@Override
	public List<Member> findAll() {//DAO문에서 사용한 것과 동일
		// 이전의 context.xml에서 DB와 연결해 사용한 것과 같이  properties에 설정해서 동일하게 사용가능
		 // 멤버테이블 조회
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}  
	private RowMapper<Member> memberRowMapper(){
		//확장 for문과 비슷
		//람다식사용 while(rs.next())과 동일하다고 생각하면됨
		return (rs, rowNum) -> { 
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}

}
