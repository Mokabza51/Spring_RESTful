package com.bibliowcs.Spring_RESTful.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bibliowcs.Spring_RESTful.Repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

  @Autowired
  BookRepository bookRespository;

  @GetMapping("/books")
  public List<Book> index(){
    return bookRespository.findAll();
  }

  @GetMapping("/books/{id}")
  public Book show(@PathVariable int id){
    return bookRespository.findById(id).get();
  }

  @PostMapping("/books/search")
  public List<Book> search(@RequestBody Map<String, String> body){
    String searchTerm = body.get("text");
    return bookRespository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
  }

  @PostMapping("/books")
  public Book create(@RequestBody Book book){
    return bookRespository.save(book);
  }

  @PutMapping("/books/{id}")
  public Book update(@PathVariable int id, @RequestBody Book book){
    // getting book
    Book bookToUpdate = bookRespository.findById(id).get();
    
    if(book.getTitle() != null) {
    	bookToUpdate.setTitle(book.getTitle());
    }
    if (book.getAuthor() != null) {
    	bookToUpdate.setAuthor(book.getAuthor());
    }
    if (book.getDescription() != null) {
    	bookToUpdate.setDescription(book.getDescription());
    }
  
    return bookRespository.save(bookToUpdate);
  }

  @DeleteMapping("books/{id}")
  public boolean delete(@PathVariable int id){
    bookRespository.deleteById(id);
    return true;
  } 
}