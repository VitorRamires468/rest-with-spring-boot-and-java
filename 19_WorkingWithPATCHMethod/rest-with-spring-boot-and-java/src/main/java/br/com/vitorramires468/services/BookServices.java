package br.com.vitorramires468.services;

import br.com.vitorramires468.controllers.BookController;
import br.com.vitorramires468.data.dto.BookDTO;
import br.com.vitorramires468.exceptions.RequiredObjectIsNullException;
import br.com.vitorramires468.exceptions.ResourceNotFoundException;
import br.com.vitorramires468.model.Book;
import br.com.vitorramires468.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.vitorramires468.mapper.ObjectMapper.parseListObjects;
import static br.com.vitorramires468.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll(){
        logger.info("Find all books");

        List<Book> books =  repository.findAll();

        var booksDTOList = parseListObjects(books, BookDTO.class);

        booksDTOList.forEach(BookServices::addHateoasLinks);

        return booksDTOList;
    }

    public BookDTO findById(int id){
        logger.info("Find book by id");

        var book = repository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Resource not found for this id"));

        var dto = parseObject(book, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public BookDTO create(BookDTO bookDTO){
        logger.info("Creating one Book");

        if(bookDTO == null) throw new RequiredObjectIsNullException();

        var book = parseObject(bookDTO, Book.class);

        var dto = parseObject(repository.save(book), BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public BookDTO update(BookDTO bookDTO){

        if(bookDTO == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book");

        Book book = repository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id"));

        book.setAuthor(bookDTO.getAuthor());
        book.setLaunchDate(bookDTO.getLaunchDate());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());

        var dto =  parseObject(repository.save(book), BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(int id){
        logger.info("Deleting one book");

        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id"));

        repository.delete(book);
    }

    private static void addHateoasLinks(BookDTO dto){
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
