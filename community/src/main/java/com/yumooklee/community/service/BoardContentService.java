package com.yumooklee.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumooklee.community.domain.Board;
import com.yumooklee.community.domain.BoardContent;
import com.yumooklee.community.repository.BoardContentRepository;
import com.yumooklee.community.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardContentService {

	private final BoardContentRepository boardContentRepository;
	
	/*
	 * 게시글 내용 등록
	 * */
	@Transactional
	public void join(BoardContent boardContent) {
		boardContentRepository.save(boardContent);
	}
	
	//게시글 전체 조회
//	public List<Board> findBoards(){
//		return boardRepository.findAll();
//	}
//	
//	@Transactional(readOnly = true)
//	public Board findOne(Long boardId) {
//		return boardRepository.findOne(boardId);
//	}
//	
//	@Transactional
//	public void update(Long id, String title) {
//		Board board = boardRepository.findOne(id);
//		board.setTitle(title);
//	}
}
