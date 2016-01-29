package com.filocha.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "applicationContext.xml")
public class BookServiceTest {

	@Autowired
	private BookService service;

	@Before
	public void clearDataBase() {
		service.clearTableValues("BookModel");
	}

	@Test
	public void shouldReturnListOfBooks() {
		BookModel model1 = new BookModel();
		BookModel model2 = new BookModel();

		model1.setId(new Random().nextInt(10000));
		model2.setId(new Random().nextInt(10000));

		int firstBookId = service.addNewBook(model1);
		int secondBookId = service.addNewBook(model2);

		List<BookModel> books = service.getBooksList();
		List<Integer> expected = books.stream().map(o -> o.getId()).collect(Collectors.toList());

		assertThat(expected, hasItems(firstBookId, secondBookId));
	}

	@Test
	public void shouldReturnBookData() {
		BookModel model = new BookModel();
		model.setId(new Random().nextInt(10000));

		int bookId = service.addNewBook(model);

		BookModel expected = service.getBookInfo(bookId);

		assertThat(expected.getId(), equalTo(bookId));
	}

	@Test
	public void shouldReserveBook() {
		BookModel model = new BookModel();
		model.setId(new Random().nextInt(10000));

		int bookId = service.addNewBook(model);
		String userEmail = "a" + new Random().nextInt(10000);

		service.reserveBook(bookId, userEmail);

		BookModel expected = service.getBookInfo(bookId);

		assertThat(expected.getId(), equalTo(bookId));
		assertThat(expected.getOwnerEmail(), equalTo(userEmail));
		assertThat(expected.isFree(), equalTo(false));
	}

	@Test
	public void shouldReturnReservedBook() {
		BookModel model = new BookModel();
		model.setId(new Random().nextInt(10000));

		int bookId = service.addNewBook(model);
		String userEmail = "a" + new Random().nextInt(10000);

		service.reserveBook(bookId, userEmail);

		BookModel expected = service.getBookInfo(bookId);

		assertThat(expected.getId(), equalTo(bookId));
		assertThat(expected.getOwnerEmail(), equalTo(userEmail));
		assertThat(expected.isFree(), equalTo(false));

		service.returnBook(userEmail);

		BookModel expected2 = service.getBookInfo(bookId);

		assertThat(expected2.getId(), equalTo(bookId));
		assertThat(expected2.getOwnerEmail(), equalTo(null));
		assertThat(expected2.isFree(), equalTo(true));
	}

}
