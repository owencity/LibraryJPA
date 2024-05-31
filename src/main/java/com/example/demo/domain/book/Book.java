package com.example.demo.domain.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;
	
	@Column(nullable = false) 
	private String name;
	
	protected Book() {
		
	}
	
	public Book(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));		
		}
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
