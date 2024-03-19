package model.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import jakarta.servlet.ServletContext;
import model.PagingUtil;
import service.DaoService;

public class BBSDao implements DaoService<BBSDto> {
	
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public BBSDao(ServletContext context) {
		try {
			//드라이버 로딩
			Class.forName(context.getInitParameter("ORACLE-DRIVER"));	
			
			//커넥션 풀로 데이타베이스 연결		
			Context ctx = new InitialContext();
			DataSource source= (DataSource)ctx.lookup("java:comp/env/ict");			
			conn = source.getConnection();			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void close() {
		try {
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
		}
		catch(SQLException e) {}
		
	}
	
	@Override
	public List<BBSDto> selectList(Map map) {
		List<BBSDto> items = new Vector<>();
		String sql="SELECT *"
				+ "		FROM ("
				+ "		  SELECT b.*,name,"
				+ "				 RANK() OVER (ORDER BY no DESC) AS no_rank"
				+ "		  FROM bbs b JOIN member m ON b.id=m.id ";
				if(map.get("searchColumn") !=null) {
					sql+=" AND "+map.get("searchColumn") + " LIKE '%"+map.get("searchWord")+"%'";
				}
				sql+= ") "
				+ "		WHERE no_rank BETWEEN ? AND ? ";	
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map.get(PagingUtil.START).toString());
			psmt.setString(2, map.get(PagingUtil.END).toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				BBSDto dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setName(rs.getString(7));
				items.add(dto);
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		return items;
	}
		
	//상세보기
	@Override
	public BBSDto selectOne(String... params) {
		BBSDto dto=null;
		
		String sql="SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, params[0]);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setName(rs.getString(7));
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return dto;
	}
	//검색
	@Override
	public int getTotalRecordCount(Map map) {
		int totalCount=0;
		String sql="SELECT COUNT(*) FROM bbs b JOIN member m ON b.id=m.id ";
		
		if(map != null && map.get("searchColumn") !=null) {
			sql+=" WHERE "+map.get("searchColumn") + " LIKE '%"+map.get("searchWord")+"%'";
		}
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount= rs.getInt(1);
		}
		catch(SQLException e) {e.printStackTrace();}
		return totalCount;
	}	
	//입력용
	@Override
	public int insert(BBSDto dto) {
		int affected=0;
		String sql="INSERT INTO bbs(no,id,title,content) VALUES(SEQ_BBS.NEXTVAL,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql,new String[] {"no"});
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			affected = psmt.executeUpdate();	
			rs= psmt.getGeneratedKeys();
			if(rs.next()) {
				System.out.println("방금 입력된 행의 키값:"+rs.getLong(1));
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}
	//수정용
	@Override
	public int update(BBSDto dto) {
		int affected=0;
		String sql="UPDATE bbs SET title=?,content=? WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(3, dto.getNo());
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			affected = psmt.executeUpdate();
			
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}	
	//삭제용
	@Override
	public int delete(BBSDto dto) {
		int affected=0;
		String sql="DELETE bbs WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNo());			
			affected = psmt.executeUpdate();			
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}
	//회원여부 판단용
	public boolean isMember(String username,String password) {
		String sql="SELECT COUNT(*) FROM member WHERE id=? AND pwd=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) return false;
		}
		catch(SQLException e) {e.printStackTrace();return false;}
		return true;
	}
	//조회수
	public void updateHitCount(String no) {
		String sql = "UPDATE bbs SET hitcount = hitcount + 1 WHERE no = ?";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, no);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
}
