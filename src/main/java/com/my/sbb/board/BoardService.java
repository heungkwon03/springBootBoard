package com.my.sbb.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(
            BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findBoardList(BoardType type) {
        //
        List<Board> boardList = boardRepository.findAll();

        List<Board> freeBoardList = boardList.stream().filter(t -> t.getType().equals(BoardType.Free)).sorted(Comparator.comparing(Board::getCreateDate).reversed()).collect(Collectors.toList());
        List<Board> noticeBoardList = boardList.stream().filter(t -> t.getType().equals(BoardType.Notice)).sorted(Comparator.comparing(Board::getCreateDate).reversed()).collect(Collectors.toList());
        List<Board> qnABoardList = boardList.stream().filter(t -> t.getType().equals(BoardType.QnA)).sorted(Comparator.comparing(Board::getCreateDate).reversed()).collect(Collectors.toList());
        List<Board> fAQBoardList = boardList.stream().filter(t -> t.getType().equals(BoardType.FAQ)).sorted(Comparator.comparing(Board::getCreateDate).reversed()).collect(Collectors.toList());

        if(type.equals(BoardType.Free)) {
            return freeBoardList;
        } else if (type.equals(BoardType.Notice)) {
            return noticeBoardList;
        } else if (type.equals(BoardType.QnA)) {
            return qnABoardList;
        } else if (type.equals(BoardType.FAQ)) {
            return fAQBoardList;
        }

        return findBoardList(type);
    }

    public Board findBoard(String id) {
        //
        if(id == null) {
            throw new NoSuchElementException("Cannot find the content");
        }

        Optional<Board> board = boardRepository.findById(id);

        return board.get();
    }

    public void addBoard(BoardDTO boardDTO) {
        //
//        if(boardDTO.getId().equals(boardRepository.findById(boardDTO.getId()))){
//            throw new IllegalArgumentException("Already exist");
//        }
        if(boardDTO.getTitle() == null) {
            throw new NoSuchElementException("title cannot be empty");
        }
        if(boardDTO.getContent() == null) {
            throw new NoSuchElementException("content cannot be empty");
        }

        BoardType type = boardDTO.getType();
        if (findBoardList(type).isEmpty()) {
            if (type == BoardType.Free) {
                Board board = boardDTO.toBoard();
                board.setNo("Free." + 1);
                boardRepository.save(board);
            } else if (type == BoardType.Notice) {
                Board board = boardDTO.toBoard();
                board.setNo("Notice." + 1);
                boardRepository.save(board);
            } else if (type == BoardType.QnA) {
                Board board = boardDTO.toBoard();
                board.setNo("QnA." + 1);
                boardRepository.save(board);
            } else if (type == BoardType.FAQ) {
                Board board = boardDTO.toBoard();
                board.setNo("FAQ." + 1);
                boardRepository.save(board);
            }
        } else if (!findBoardList(type).isEmpty()) {
            if (type == BoardType.Free) {
                Board board = boardDTO.toBoard();
                String recentNo = findBoardList(BoardType.Free).get(0).getNo();
                String[] nos = recentNo.split("[.]");
                board.setNo(nos[0] + "." + (Integer.valueOf(nos[1]) + 1));
                boardRepository.save(board);
            } else if (type == BoardType.Notice) {
                Board board = boardDTO.toBoard();
                String recentNo = findBoardList(BoardType.Notice).get(0).getNo();
                String[] nos = recentNo.split("[.]");
                board.setNo(nos[0] + "." + (Integer.valueOf(nos[1]) + 1));
                boardRepository.save(board);
            } else if (type == BoardType.QnA) {
                Board board = boardDTO.toBoard();
                String recentNo = findBoardList(BoardType.QnA).get(0).getNo();
                String[] nos = recentNo.split("[.]");
                board.setNo(nos[0] + "." + (Integer.valueOf(nos[1]) + 1));
                boardRepository.save(board);
            } else if (type == BoardType.FAQ) {
                Board board = boardDTO.toBoard();
                String recentNo = findBoardList(BoardType.FAQ).get(0).getNo();
                String[] nos = recentNo.split("[.]");
                board.setNo(nos[0] + "." + (Integer.valueOf(nos[1]) + 1));
                boardRepository.save(board);
            }
        }
    }

    public void updateBoard(String id, BoardDTO boardDTO) {
        //
        Optional<Board> targetBoard = boardRepository.findById(id);

        if(targetBoard.isPresent()) {
            Board changeBoard = targetBoard.get();
            changeBoard.setTitle(boardDTO.getTitle());
            changeBoard.setContent(boardDTO.getContent());

//            if(!targetBoard.get().getName().equals(boardDTO.getName())) {
//                throw new NoSuchElementException("Cannot change the name");
//            }
//            if(!targetBoard.get().getNo().equals(boardDTO.getNo())) {
//                throw new NoSuchElementException("Cannot change the no");
//            }
            if(changeBoard.getTitle() == null) {
                throw new NoSuchElementException("Title cannot be empty");
            }
            if(changeBoard.getContent() == null) {
                throw new NoSuchElementException("Content cannot be empty");
            }
            boardRepository.save(changeBoard);
        } else {
            throw new NoSuchElementException("Cannot find the content");
        }
    }

    public void deleteBoard(String id) {
        //
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Cannot find the content");
        }
    }
}
