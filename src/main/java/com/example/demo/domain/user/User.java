package com.example.demo.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.user.loanhistory.UserLoanHistory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	
	@Id 
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id = null;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	private Integer age;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserLoanHistory> userLoanHistories = new ArrayList<>();
	
	public User(String name, Integer age) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
			
		}
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
	
	public void updateName(String name) {
		this.name = name;
	}
	public void loanBook(String bookName) {
		this.userLoanHistories.add(new UserLoanHistory(this, bookName ));
	}
	
	public void returnBook(String bookname) {
		UserLoanHistory targetHistory = this.userLoanHistories.stream()
				.filter(history -> history.getBookName().equals(bookname))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
			targetHistory.doReturn();
	}
	
}
