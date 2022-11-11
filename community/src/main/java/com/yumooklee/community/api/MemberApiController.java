package com.yumooklee.community.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yumooklee.community.domain.Member;
import com.yumooklee.community.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;
	
	@PostMapping("/api/registMember")
	public RegistMemberResponse registMember(@RequestBody @Valid RegistMemberRequest request) {
		Member member = new Member();
		member.setName(request.getName());
		
		Long id = memberService.join(member);
		
		return new RegistMemberResponse(id);
	}
	
	@PutMapping("/api/updateMember/{id}")
	public UpdateMemberResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
		memberService.update(id, request.getName());
		Member findMember = memberService.findOne(id);
		
		return new UpdateMemberResponse(findMember.getId(), findMember.getName());
	}
	
	@Data
	static class RegistMemberRequest {
		private String name;
	}
	
	@Data
	static class RegistMemberResponse {
		private Long id;
		
		public RegistMemberResponse(Long id) {
			this.id = id;
		}
	}
	
	@Data
	static class UpdateMemberRequest {
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse{
		private Long id;
		private String name;
	}
	
}
