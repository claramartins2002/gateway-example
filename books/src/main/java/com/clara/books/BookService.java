package com.clara.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Page<Book> findAll(Pageable pageable){
            return bookRepository.findAll(pageable);
    }

    public Book findById(String id) {
        Optional<Book> optional= bookRepository.findById(Long.valueOf(id));
        return optional.orElseThrow(() -> new EntityNotFoundException("Book not found!"));
    }

    public Book save (Book book) { return  bookRepository.save(book);}

    public ResponseEntity<Book> delete (Book book) { bookRepository.delete(book);
        return null;
    }
    public Book edit (Book book, String id) { Book instancia = this.findById(id);
        return bookRepository.save(book);}
}
