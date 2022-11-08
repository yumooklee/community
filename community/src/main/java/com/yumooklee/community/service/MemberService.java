package com.yumooklee.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumooklee.community.domain.Member;
import com.yumooklee.community.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	/*
	 * 회원가입
	 * */
	@Transactional
	public Long join(Member member) {
		
		memberRepository.save(member);
		
		return member.getId();
	}
	
	//회원 전체 조회
//	public List<Member> findMembers(){
//		return memberRepository.findAll();
//	}
	
	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepository.findOne(id);
		member.setName(name);
	}
}
