package com.example.demo.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.book.Book;
import com.example.demo.domain.book.BookRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.loanhistory.UserLoanHistoryRepository;
import com.example.demo.dto.book.request.BookCreateRequest;
import com.example.demo.dto.book.request.BookLoanRequest;
import com.example.demo.dto.book.request.BookReturnRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	private final UserLoanHistoryRepository userLoanHistoryRepository;
	private final UserRepository userRepository;
	
	

	
	@Transactional
	public void saveBook(BookLoanRequest  request) {
		bookRepository.save(new Book(request.getBookName()));
	}
	
	@Transactional
	public void loanBook(BookLoanRequest request) {
		
		Book book = bookRepository.findByName(request.getBookName())
		.orElseThrow(IllegalArgumentException::new);
	if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
		throw new IllegalArgumentException("대출되어 있는 책입니다.");
		
	}
	
		User user = userRepository.findByName(request.getUserName())
			 .orElseThrow(IllegalArgumentException::new);
			user.loanBook(book.getName());
	
	}
	
	@Transactional
	public void returnBook(BookReturnRequest request) {
		User user = userRepository.findByName(request.getUserName())
				.orElseThrow(IllegalArgumentException::new);
			
		user.returnBook(request.getBookName());
	}

}
