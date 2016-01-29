package com.filocha.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filocha.hibernate.BookModel;
import com.filocha.hibernate.BookService;

/**
 * Defines methods to manage http requests received from client associated with
 * book operations
 *
 */
@RestController
public class BookDataController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "getBooksList", method = RequestMethod.GET)
	public List<BookModel> getBooksList() {
		List<BookModel> response = bookService.getBooksList();
		return response;
	}

	@RequestMapping(value = "getBookInfo", method = RequestMethod.POST)
	public BookModel getBookInfo(@RequestBody int bookId) {
		BookModel response = bookService.getBookInfo(bookId);
		return response;
	}

	@RequestMapping(value = "reserveBook", method = RequestMethod.POST)
	public boolean reserveBook(@RequestBody BookRequestModel bookRequestModel) {
		boolean response = bookService.reserveBook(bookRequestModel.getBookId(), bookRequestModel.getUserEmail());

		return response;
	}

	@RequestMapping(value = "returnBook", method = RequestMethod.POST)
	public boolean returnBook(@RequestBody String userEmail) {
		boolean response = bookService.returnBook(userEmail);

		return response;
	}
}
