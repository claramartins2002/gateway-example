package com.clara.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> getAll(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Book> bookPage = bookService.findAll(pageable);
        if(bookPage.isEmpty()){
            return new ResponseEntity<>(NOT_FOUND);
        }
        else {
            return new ResponseEntity<Page<Book>>(bookPage, OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable(value="id") String id){
        Book found= bookService.findById(id);

        return new ResponseEntity<Book>(found, OK);
    }

    @PutMapping("/{id}")
    public Book edit(@PathVariable(value="id") String id, @RequestBody Book book){
        Book found= bookService.findById(id);

        return bookService.edit(book, book.getId().toString());

    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        return new ResponseEntity<Book>(bookService.save(book), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable(value="id") String id) {
        Book book= bookService.findById(id);

        return bookService.delete(book);
    }
}
