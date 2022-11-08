package com.yumooklee.community.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yumooklee.community.domain.Member;
import com.yumooklee.community.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	EntityManager em;

	@Test
	@Rollback(false)
	public void testJoin() throws Exception {
		//given
		Member member = new Member();
		member.setName("Hwang");
		
		//when
		Long savedId = memberService.join(member);
		
		//then
		assertEquals(member, memberRepository.findOne(savedId));
	}

}
