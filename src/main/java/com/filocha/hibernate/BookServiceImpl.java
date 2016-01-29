package com.filocha.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<BookModel> getBooksList() {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			List<BookModel> booksList = (List<BookModel>) s.createQuery("FROM BookModel").list();
			return booksList;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public BookModel getBookInfo(int bookId) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			BookModel result = new BookModel();

			List<BookModel> bookInfo = (List<BookModel>) s
					.createQuery("FROM BookModel ID WHERE id = " + "'" + bookId + "'").list();

			for (Iterator<BookModel> iterator = bookInfo.iterator(); iterator.hasNext();) {
				BookModel book = iterator.next();
				result.setId(book.getId());
				result.setAuthor(book.getAuthor());
				result.setFree(book.isFree());
				result.setOwnerEmail(book.getOwnerEmail());
				result.setTitle(book.getTitle());
			}
			return result;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean reserveBook(int bookId, String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			BookModel updateBookInfo = new BookModel();
			boolean result = false;

			List<BookModel> bookList = (List<BookModel>) s.createQuery("FROM BookModel").list();
			for (Iterator<BookModel> iterator = bookList.iterator(); iterator.hasNext();) {
				BookModel book = iterator.next();

				String mail = book.getOwnerEmail();
				if (mail != null && mail.equals(userEmail)) {
					return result;
				}
			}
			List<BookModel> bookToRent = (List<BookModel>) s
					.createQuery("FROM BookModel ID WHERE id = " + "'" + bookId + "'").list();

			for (Iterator<BookModel> iterator = bookToRent.iterator(); iterator.hasNext();) {
				BookModel book = iterator.next();
				updateBookInfo.setAuthor(book.getAuthor());
				updateBookInfo.setFree(false);
				updateBookInfo.setId(book.getId());
				updateBookInfo.setOwnerEmail(userEmail);
				updateBookInfo.setTitle(book.getTitle());
				updateBook(updateBookInfo);
				result = true;

			}
			return result;
		});
	}

	public Void updateBook(BookModel bookModel) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.update(bookModel);
			return null;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean returnBook(String userEmail) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			BookModel bookModel = new BookModel();
			boolean result = false;

			List<BookModel> bookToReturn = (List<BookModel>) s
					.createQuery("FROM BookModel OWNEREMAIL WHERE ownerEmail = " + "'" + userEmail + "'").list();

			for (Iterator<BookModel> iterator = bookToReturn.iterator(); iterator.hasNext();) {
				BookModel book = iterator.next();
				bookModel.setId(book.getId());
				bookModel.setTitle(book.getTitle());
				bookModel.setAuthor(book.getAuthor());
				bookModel.setFree(true);
				bookModel.setOwnerEmail(null);

				updateBook(bookModel);
				result = true;
			}
			return result;
		});
	}

	@Override
	public int addNewBook(BookModel model) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {

			s.save(model);

			return model.getId();
		});
	}

	@Override
	public Void clearTableValues(String modelClassName) {
		return SessionFactoryExtensions.run(sessionFactory, s -> {
			s.createQuery("DELETE " + modelClassName).executeUpdate();

			return null;
		});
	}
}
