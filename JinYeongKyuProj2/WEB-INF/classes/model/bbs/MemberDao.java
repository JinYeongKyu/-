package model.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import service.DaoService;

public class MemberDao implements DaoService<MemberDto>{
	
		private Connection conn;
		private ResultSet rs;
		private PreparedStatement psmt;
		
		public MemberDao(ServletContext context) {
			try {
				//드라이버 로딩
				Class.forName(context.getInitParameter("ORACLE-DRIVER"));					
				//커넥션 풀 연결		
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
	public List<MemberDto> selectList(Map map) {
		
		return null;
	}

	@Override
	public MemberDto selectOne(String... params) {
		
		MemberDto dto=null;	
		String sql="SELECT * FROM member WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, params[0]);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDto();
				dto.setGender(rs.getString(4));
				dto.setInters(rs.getString(5));
				dto.setPwd(rs.getString(2));
				dto.setId(rs.getString(1));
				dto.setEducation(rs.getString(6));
				dto.setName(rs.getString(3));
				dto.setSelfintroduce(rs.getString(7));
			}
		}
		catch(SQLException e) {e.printStackTrace();}		
		return dto;
	}

	@Override
	public int getTotalRecordCount(Map map) {
		
		return 0;
	}
	//입력
	@Override
	public int insert(MemberDto dto) {
		int affected=0;
		String sql="INSERT INTO member(id,pwd,name,gender,inters,education,selfintroduce) VALUES(?,?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getGender());
			psmt.setString(5, dto.getInters());
			psmt.setString(6, dto.getEducation());
			psmt.setString(7, dto.getSelfintroduce());
			affected = psmt.executeUpdate();
			rs= psmt.getGeneratedKeys();
			if(rs.next()) {
				System.out.println("입력된 행의 키값:"+rs.getLong(1));
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}
	//수정
	@Override
	public int update(MemberDto dto) {
		int affected=0;
		String sql="UPDATE member SET pwd=?,name=?,gender=?,inters=?,education=?,selfintroduce=? WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPwd());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getGender());
			psmt.setString(4, dto.getInters());
			psmt.setString(5, dto.getEducation());
			psmt.setString(6, dto.getSelfintroduce());
			psmt.setString(7, dto.getId());
			affected = psmt.executeUpdate();			
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}
	//삭제
	@Override
	public int delete(MemberDto dto) {
		int affected=0;
		String sql="DELETE member WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());			
			affected = psmt.executeUpdate();			
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}
	
}
