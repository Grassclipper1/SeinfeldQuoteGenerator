package com.example.SeinfeldQuoteGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;



    public void quoteCreator() {
        File jsonFile = new File("quotes.js");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // takes all the data in the quotes.js and puts it into a new list. from that list extract into quote-class.
            List<Object> rawQuotes = objectMapper.readValue(jsonFile, List.class);
            for (Object rawQuote : rawQuotes) {
                String quote = (String) ((java.util.LinkedHashMap) rawQuote).get("quote");
                String author = (String) ((java.util.LinkedHashMap) rawQuote).get("author");
                Quote newQuote = new Quote(quote, author);
                quoteRepository.save(newQuote);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Quote findByAuthor(String author) {
        List<Quote> quotes = new ArrayList<>(quoteRepository.getQuoteByAuthor(author));
        Random rand = new Random();
        Quote quote = quotes.get(rand.nextInt(quotes.size()));
        return quote;
        //gets all quotes from a specific character and puts them into a list. Then from that list chooses one on random.

    }
}

