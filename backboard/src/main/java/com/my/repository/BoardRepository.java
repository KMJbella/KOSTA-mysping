package com.my.repository;

import java.util.List;
import com.my.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

public interface BoardRepository {
  /*
   * 1) 게시물목록(페이지별 3건씩 보여주기) 2) 게시글 검색 3) 게시글 상세보기 조회수 1증가 4) 게시글 수정하기 5) 게시글 삭제하기 6) 답글쓰기 7) 글쓰기
   */
  /**
   * 게시물목록
   * 
   * @param currentPage 검색할 페이지번호
   * @param cntPerPage 페이지별 보여줄 건수
   * @return
   */
  List<Board> selectByPage(int currentPage, int cntPerPage) throws FindException;

  /**
   * 전체행 수 검색한다
   * 
   * @return
   * @throws FindException
   */
  int selectCount() throws FindException;

  /**
   * 검색어를 포함한 게시글제목 또는 검색어를 포함한 게시자ID를 갖는 게시글들을 검색한다
   * 
   * @param word
   * @return
   * @throws FindException
   */
  int selectCount(String word) throws FindException;

  /**
   * 검색어를 포함한 게시글제목 또는 검색어를 포함한 게시자ID를 갖는 게시글들을 검색한다
   * 
   * @param word 검색어
   * @param currentpage 검색할 페이지번호
   * @param cntPerPage 페이지별 보여줄 건수
   * @return
   * @throws FindException
   * 
   */
  List<Board> selectByWord(String word, int currentpage, int cntPerPage) throws FindException;

  /**
   * 글번호에 해당하는 게시글을 검색한다
   * 
   * @param boardNo 게시글번호
   * @return 게시글객체
   * @throws FindException
   */
  Board selectByBoardNo(int boardNo) throws FindException;

  /**
   * 게시글 수정한다(조회수1증가/게시글내용 수정하기...에 사용)
   * 
   * @param board 게시글객체
   * @throws ModifyException
   */

  void update(Board board) throws ModifyException;

  /**
   * 게시글 삭제
   * 
   * @param boardNo 게시글번호
   * @throws RemoveException
   */
  void delete(int boardNo) throws RemoveException;

  /**
   * 게시글을 추가한다, 답글을 추가한다
   * 
   * @param board
   * @throws AddException
   */
  void insert(Board board) throws AddException;



}


