package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service		
@RequiredArgsConstructor	// 생성자를 호출할 때 필요한 파라미터를 채워 초기화 시키라는 의미(@Autowired 생략 가능)
public class BoardService {

	private final BoardRepository boardRepository;

	private final ReplyRepository replyRepository;
	
	@Transactional	
	public void saveContent(Board board, User user) {	// title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public Board boardView(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 해당 글을 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void updateById(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 수정 실패 : 해당 글을 찾을 수 없습니다.");
				});	// 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시(Service가 종료될 때)에 트랜잭션이 종료 -> 더티체킹 -> 자동 업데이트(flush)
	}
	
	@Transactional
	public void saveReply(ReplySaveRequestDto replySaveRequestDto) {	// Dto를 사용하면 data의 운반과 관리가 편리하다.

		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		
//		System.out.println(reply); -> 오브젝트를 출력하게 되면 자동으로 toString()이 호출된다.
		
//		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 작성 실패 : 해당 유저를 찾을 수 없습니다.");
//		});
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//					return new IllegalArgumentException("댓글 작성 실패 : 해당 글을 찾을 수 없습니다.");
//				});
//		
//		Reply reply = new Reply();
//		reply.update(user, board, replySaveRequestDto.getContent());
//		
//		replyRepository.save(reply);
	}
	
	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}
