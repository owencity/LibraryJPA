package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class UserJdbcRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean isUserNotExist(long id) {
		String readSql = "SELECT * FORM user WHERE id = ?";
		return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
	}
	
	public void updateUserName(String name, long id) {
		String sql = "UPDATE user SET name = ? WHERE id = ?";
		jdbcTemplate.update(sql, name, id);
	}
	
	public boolean isUserNotExist(String name) {
		String readSql = "SELECT * FROM user WHERE name = ?";
		return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();	
	}
	public void deleteUser(String name) {
		String sql = "DELETE FROM user WHERE name= ?";
		jdbcTemplate.update(sql, name);
	}
	
	public void saveUser(String name, Integer age) {
		String sql = "INSERT INTO user (name, age) VALUES (?, ?";
		jdbcTemplate.update(sql, name, age);
	}
}
