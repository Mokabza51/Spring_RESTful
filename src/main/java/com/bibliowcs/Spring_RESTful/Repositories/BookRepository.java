package com.bibliowcs.Spring_RESTful.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliowcs.Spring_RESTful.Controllers.Book;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {
	
	List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
    
}

