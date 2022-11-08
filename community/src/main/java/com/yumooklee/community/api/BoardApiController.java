package com.yumooklee.community.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yumooklee.community.domain.Board;
import com.yumooklee.community.service.BoardService;

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
	
//	@GetMapping("/api/searchBoard")
//	public Result searchBoard() {
//	 List<Board> findBoards = boardService.findBoards();
//	 
//	 //엔티티 -> DTO 변환
//	 List<BoardDto> collect = findBoards.stream()
//			 .map(m -> new BoardDto(m.getTitle()))
//			 .collect(Collectors.toList());
//	 
//	return new Result(collect);
//	}
	 
	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
	 
	@Data
	@AllArgsConstructor
	static class BoardDto {
		private String name;
	}
	
	@PostMapping("/api/registBoard")
	public CreateBoardResponse registBoard(@RequestBody @Valid CreateBoardRequest request) {
		Board board = new Board();
		board.setTitle(request.getTitle());
		
		Long id = boardService.join(board);
		return new CreateBoardResponse(id);
	}
	
	@PutMapping("/api/updateBoard/{id}")
	public UpdateBoardResponse updateBoard(@PathVariable("id") Long id, @RequestBody @Valid UpdateBoardRequest request) {
		boardService.update(id, request.getTitle());
		Board findBoard = boardService.findOne(id);
		return new UpdateBoardResponse(findBoard.getId(), findBoard.getTitle());
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
	
	@Data
	static class CreateBoardRequest {
		private String title;
	}
	
	@Data
	static class CreateBoardResponse {
		private Long id;
		
		public CreateBoardResponse(Long id) {
			this.id = id;
		}
	}
}
