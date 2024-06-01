package com.example.demo.controller.user.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.book.request.BookCreateRequest;
import com.example.demo.dto.book.request.BookLoanRequest;
import com.example.demo.dto.book.request.BookReturnRequest;
import com.example.demo.service.book.BookService;


@RestController
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/book")
	public void saveBook(@RequestBody BookCreateRequest request) {
		bookService.saveBook(request);
	}
	
	@PostMapping("/book/loan")
	public void loanBook(@RequestBody BookLoanRequest request) {
		bookService.loanBook(request);
	}
	
	@PutMapping("/book/return")
	public void returnBook(@RequestBody BookReturnRequest requset) {
		bookService.returnBook(requset);
	}

}
