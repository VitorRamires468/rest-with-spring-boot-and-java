package br.com.vitorramires468.controllers;

import br.com.vitorramires468.controllers.docs.BookControllerDocs;
import br.com.vitorramires468.data.dto.BookDTO;
import br.com.vitorramires468.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController implements BookControllerDocs{

    @Autowired
    private BookServices service;

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable int id){
        BookDTO bookDTO = service.findById(id);

        return bookDTO;
    }

    @GetMapping
    public List<BookDTO> findAll(){
        return  service.findAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO){
            return service.create(bookDTO);
    }

    @PutMapping
    public BookDTO update(@RequestBody BookDTO bookDTO){
        return service.update(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
