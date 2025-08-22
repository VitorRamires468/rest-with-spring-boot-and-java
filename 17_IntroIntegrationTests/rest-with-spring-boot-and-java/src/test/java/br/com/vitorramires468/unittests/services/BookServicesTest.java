package br.com.vitorramires468.unittests.services;

import br.com.vitorramires468.data.dto.BookDTO;
import br.com.vitorramires468.exceptions.RequiredObjectIsNullException;
import br.com.vitorramires468.model.Book;
import br.com.vitorramires468.repository.BookRepository;
import br.com.vitorramires468.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp(){
      input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById(){
        Book book = input.mockEntity(1);
        book.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(book));

        var result =service.findById(1);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("book")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Author1", result.getAuthor());
        assertEquals("2003-02-08", result.getLaunchDate().toString());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());

    }

    @Test
    void create(){
        Book entity = input.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1);

        BookDTO bookDTO = input.mockDTO(1);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("book")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Author1", result.getAuthor());
        assertEquals("2003-02-08", result.getLaunchDate().toString());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());

    }

    @Test
    void createWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void update(){
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1);

        BookDTO bookDTO = input.mockDTO(1);

        when(repository.findById(1)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);

        var result = service.update(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("book")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Author1", result.getAuthor());
        assertEquals("2003-02-08", result.getLaunchDate().toString());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());
    }

    @Test
    void updateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void delete(){
        Book entity = input.mockEntity(1);

        when(repository.findById(1)).thenReturn(Optional.of(entity));

        service.delete(1);

        verify(repository, times(1)).findById(anyInt());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll(){
        List<Book> bookList = input.mockEntityList();

        when(repository.findAll()).thenReturn(bookList);

        List<BookDTO> bookDTOList = service.findAll();

        assertNotNull(bookDTOList);
        assertEquals(14, bookDTOList.size());

        var bookOne = bookDTOList.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("book")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Author1", bookOne.getAuthor());
        assertEquals("2003-02-08", bookOne.getLaunchDate().toString());
        assertEquals(1.0, bookOne.getPrice());
        assertEquals("Title1", bookOne.getTitle());

        var bookFour = bookDTOList.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("book")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Author4", bookFour.getAuthor());
        assertEquals("2003-02-08", bookFour.getLaunchDate().toString());
        assertEquals(4.0, bookFour.getPrice());
        assertEquals("Title4", bookFour.getTitle());
    }
}