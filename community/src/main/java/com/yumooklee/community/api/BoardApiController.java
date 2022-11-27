package com.yumooklee.community.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yumooklee.community.domain.Board;
import com.yumooklee.community.domain.BoardContent;
import com.yumooklee.community.domain.Category;
import com.yumooklee.community.domain.Member;
import com.yumooklee.community.service.BoardContentService;
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
	private final BoardContentService boardContentService;
	
	@GetMapping("/api/searchBoard")
	public Result searchBoard() {
		List<Board> findBoards = boardService.findBoards();
	
		//엔티티 -> DTO 변환@@@
		List<BoardDto> collect = findBoards.stream()
				.map(m -> new BoardDto(m.getId(), m.getCategory().getId(), m.getMember().getId(), m.getTitle()))
				.collect(Collectors.toList());
		
		return new Result("SUCCESS", collect);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		String result;
		private T board;
	}
	
	@Data
	@AllArgsConstructor
	static class BoardDto {
		private Long boardId;
		private Long categoryId;
		private Long memberId;
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
		
		for (int i = 0; i < request.boardContentList.size(); i++) {
			BoardContent requestBoardContent = request.boardContentList.get(i);
			
			BoardContent boardContent = new BoardContent();
			boardContent.setBoard(board);
			boardContent.setContent(requestBoardContent.getContent());
			boardContent.setContentSeq(requestBoardContent.getContentSeq());
			
			boardContentService.join(boardContent);
		}
		
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
		private List<BoardContent> boardContentList;
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
