package br.com.vitorramires468.unittests.mapper.mocks;

import br.com.vitorramires468.data.dto.BookDTO;
import br.com.vitorramires468.model.Book;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookDTO mockDTO(){
        return mockDTO(0);
    }

    public List<Book> mockEntityList(){
        List<Book> bookList = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            bookList.add(mockEntity(i));
        }
        return bookList;
    }

    public List<BookDTO> mockDTOList(){
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (int i = i = 0; i < 14; i++) {
            bookDTOList.add(mockDTO(i));
        }
        return bookDTOList;
    }

    public Book mockEntity(int i) {
        Book book = new Book();
        book.setAuthor("Author"+i);
        book.setLaunchDate(LocalDate.of(2003, 2, 8));
        book.setPrice(i*1.0);
        book.setId(i);
        book.setTitle("Title"+i);
        return book;
    }

    public BookDTO mockDTO(int i) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Author"+i);
        bookDTO.setLaunchDate(LocalDate.of(2003, 2, 8));
        bookDTO.setPrice(i*1.0);
        bookDTO.setId(i);
        bookDTO.setTitle("Title"+i);
        return bookDTO;
    }


}
