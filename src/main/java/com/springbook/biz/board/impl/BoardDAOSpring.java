package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAOSpring extends JdbcDaoSupport {
    //sql 명령어들
    private final String BOARD_INSERT = "insert into board(seq, title, writer, content)" +
            "values((select nvl(max(seq),0)+1 from board),?,?,?)";
    private final String BOARD_UPDATE = "updte board set title =?, content=? where seq=?";
    private final String BOARD_DELETE = "delete voard where seq =?";
    private final String BOARD_GET = " select * from board where seq =?";
    private final String BOARD_LIST = "select * from board order where seq = ?";

    @Autowired
    public void setSuperDataSource(DataSource dataSource){
        super.setDataSource(dataSource);
    }
    //CRUD 기능의 메소드 구현
    //글등록
    public void insertBoard(BoardVO vo){
        System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_INSERT,vo.getTitle(), vo.getWriter(), vo.getContent());
    }
    //글 수정
    public void updateBoard(BoardVO vo){
        System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_UPDATE,vo.getTitle(), vo.getContent(), vo.getSeq());
    }
    //글 삭제
    public void deleteBoard(BoardVO vo){
        System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
    }
    //글 상세 조회
    public BoardVO getBoard(BoardVO vo){
        System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
        Object[] args = {vo.getSeq()};
        return getJdbcTemplate().queryForObject(BOARD_GET,args,new BoardRowMapper());
    }
    //글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo){
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
    }
}
class BoardRowMapper implements RowMapper<BoardVO>{
    public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
        BoardVO board = new BoardVO();
        board.setSeq(rs.getInt("SEQ"));
        board.setTitle(rs.getString("TITLE"));
        board.setWriter(rs.getString("WRITER"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDate(rs.getDate("REGDATE"));
        board.setCnt(rs.getInt("CNT"));
        return board;
    }
}
