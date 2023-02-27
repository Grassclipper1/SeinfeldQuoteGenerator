package com.example.SeinfeldQuoteGenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteRepository extends JpaRepository <Quote, Long>{

    //picks quotes from the database with the specified character and puts them in a list.
    @Query(value = "SELECT * FROM QUOTE where author LIKE :author", nativeQuery = true)
    List<Quote> getQuoteByAuthor(@Param("author") String author);
}
