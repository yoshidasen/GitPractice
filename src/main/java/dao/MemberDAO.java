package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class MemberDAO {
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerMember(Member member) {
		String sql = "INSERT INTO member VALUES(default,?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		
		String salt = GenerateSalt.getSalt(32);
		
		String hashedPw = GenerateHashedPw.getSafetyPassword(member.getPassword(), salt);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, member.getName());
			pstmt.setInt(2, member.getAge());
			pstmt.setInt(3, member.getGender());
			pstmt.setInt(4, member.getPhone_number());
			pstmt.setString(5, member.getMail());
			pstmt.setString(6, hashedPw);
			pstmt.setString(7, salt);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.err.println(result+"件更新しました。");
		}
		return result;
	}
	
	public static List<Member> selectAllMembers() {
		List<Member> result = new ArrayList<>();
		
		String sql = "SELECT * FROM member";
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					int gender = rs.getInt("gender");
					int phone_number = rs.getInt("phone_number");
					String mail = rs.getString("mail");
					String password = rs.getString("password");
					String salt = rs.getString("salt");
					
					Member member = new Member(id,name,age,gender,phone_number,mail,password,salt);
					
					result.add(member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int DeleteMember(String mail) {
		String sql = "DELETE FROM member WHERE mail=?";
		int result = 0;
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1,mail);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result+"件削除しました");
		}
		return result;
	}

	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM member WHERE mail = ?";
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1,mail);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Member login(String mail,String hashedPw) {
		String sql = "SELECT * FROM member WHERE mail = ? AND password = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					int gender = rs.getInt("gender");
					int phone_number = rs.getInt("phone_number");
					String salt = rs.getString("salt");
					
					return new Member(id,name,age,gender,phone_number,null,null,salt);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
