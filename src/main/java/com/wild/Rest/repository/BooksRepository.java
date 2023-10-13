package com.wild.Rest.repository;


import com.wild.Rest.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

   //public List<Books> findByTitleOrByDescription (String title, String description);
    public List<Books> findByTitleContainingOrDescriptionContaining(String title,String description);
}