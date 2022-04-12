package library.wslibrarycheckout.library.service;

import library.wslibrarycheckout.library.dao.AuthorRepository;
import library.wslibrarycheckout.library.dao.BookRepository;
import library.wslibrarycheckout.library.entity.Author;
import library.wslibrarycheckout.library.entity.Book;
import library.wslibrarycheckout.library.model.BookDTO;
import library.wslibrarycheckout.library.exceptionhandling.CannotBeFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addBook(Book book) {

        authorRepository.save(book.getAuthor());
        return  bookRepository.save(book);
    }

    public Book removeBookById(int bookId) {
        Optional<Book> mayBeBook = bookRepository.findById(bookId);
        if(mayBeBook.isPresent()){
            bookRepository.delete(mayBeBook.get());
        } else {
            throw new CannotBeFoundException("Book with id" + " "+ " cannot be found");
        }
        return null;
    }

    public Optional<Book> getBookById(int bookId) {

        Optional<Book> mayBeBook = bookRepository.findById(bookId);

        if(mayBeBook.isPresent()){
            return mayBeBook;
        } else {
            throw new CannotBeFoundException("Book with id " + bookId+ " cannot be found");
        }
    }

    public Book updateBookById(int bookId, BookDTO bookDTO) {
        Optional<Book> mayBeBook = bookRepository.findById(bookId);

        if(mayBeBook.isPresent()){
           mayBeBook.get().setAvailabilty(bookDTO.getAvailability());
           mayBeBook.get().setExpectedReturnDate((bookDTO.getExpectedReturnDate()));
           bookRepository.save(mayBeBook.get());
        } else {
            throw new CannotBeFoundException("Book with id " + bookId+ " cannot be found, so update is not successful!");
        }
        return null;
    }

    public List<Book> getBookByAuthorId(String authorName) {


        List<Book> bookList = bookRepository.findAll();

       List<Book> bookForAuthor = bookList.stream().filter(book -> book.getAuthor().getFullName().equalsIgnoreCase(authorName)).collect(Collectors.toList());

        return bookForAuthor;
    }
}
