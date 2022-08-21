package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
@Repository(value = "customerRepository")
public class CustomerOracleRepository implements CustomerRepository {
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public void insert(Customer customer) throws AddException {
		SqlSession session = null;
		
		try {
			 session = sessionFactory.openSession(); //Connection과 같은 뜻
			 session.insert("com.my.mapper.CustomerMapper.insert", customer);
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close(); //DBCP에게 Connection 돌려줌
			}
		  }
	   }

	@Override
	public Customer selectById(String id) throws FindException{
		SqlSession session = null;
	try {
		 session = sessionFactory.openSession(); //Connection과 같은 뜻
		 Customer c =session.selectOne("com.my.mapper.CustomerMapper.selectById", id);

		if(c == null) {
			throw new FindException("고객이 없습니다"); //재사용성이 높은 메세지로 만들 것
			
		}
		return c;
	}catch(Exception e) {
		e.printStackTrace();
		throw new FindException(e.getMessage());
	}finally {
		if(session != null) {
			session.close(); //DBCP에게 Connection 돌려줌
		}
		
	}
		/*
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectIdDupChkSQL = "SELECT * FROM customer WHERE id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getInt("status"),
						rs.getString("buildingno")
						);
			}
			throw new FindException("고객이 없습니다"); //재사용성이 높은 메세지로 만들 것
													//상세메세지는 컨트롤러에서 만들것, repository에서x
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}*/
	}

}
