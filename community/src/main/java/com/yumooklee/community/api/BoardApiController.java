package com.yumooklee.community.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yumooklee.community.domain.Board;
import com.yumooklee.community.domain.Category;
import com.yumooklee.community.domain.Member;
import com.yumooklee.community.service.BoardService;
import com.yumooklee.community.service.CategoryService;
import com.yumooklee.community.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

	private final BoardService boardService;
	private final CategoryService categoryService;
	private final MemberService memberService;
	
	@GetMapping("/api/searchBoard")
	public Result searchBoard() {
		List<Board> findBoards = boardService.findBoards();
	
		//엔티티 -> DTO 변환
		List<BoardDto> collect = findBoards.stream()
				.map(m -> new BoardDto(m.getId(), m.getCategory(), m.getMember(), m.getTitle()))
				.collect(Collectors.toList());
	
		return new Result(collect);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	static class BoardDto {
		private Long id;
		private Category category;
		private Member member;
		private String title;
	}
	
	@PostMapping("/api/registBoard")
	public RegistBoardResponse registBoard(@RequestBody @Valid RegistBoardRequest request) {
		Category category = categoryService.findOne(request.getCategoryId());
		Member member = memberService.findOne(request.getMemberId());
		
		Board board = new Board();
		board.setCategory(category);
		board.setMember(member);
		board.setTitle(request.getTitle());
		
		Long id = boardService.join(board);
		
		return new RegistBoardResponse(id);
	}
	
	@PutMapping("/api/updateBoard/{id}")
	public UpdateBoardResponse updateBoard(@PathVariable("id") Long id, @RequestBody @Valid UpdateBoardRequest request) {
		boardService.update(id, request.getTitle());
		Board findBoard = boardService.findOne(id);
		
		return new UpdateBoardResponse(findBoard.getId(), findBoard.getTitle());
	}
	
	@Data
	static class RegistBoardRequest {
		private Long categoryId;
		private Long memberId;
		private String title;
	}
	
	@Data
	static class RegistBoardResponse {
		private Long id;
		
		public RegistBoardResponse(Long id) {
			this.id = id;
		}
	}
	
	@Data
	static class UpdateBoardRequest {
		private String title;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateBoardResponse{
		private Long id;
		private String title;
	}
	
}
