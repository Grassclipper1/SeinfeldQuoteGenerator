package com.example.SeinfeldQuoteGenerator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuoteController {

    @Autowired
    QuoteService quoteService;
    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping ("/")
    String landingPage(Model model, @RequestParam(value = "author", required = false) String author){
        if (!quoteRepository.existsById(1L)) {
            quoteService.quoteCreator();
            //checks if there are quotes, otherwise calls upon method to create them. happens first time.
        }
        if (author != null){
        Quote quote = quoteService.findByAuthor(author);
        model.addAttribute(quote);
        //adds a quote from the specific character
        }

        return "landingPage";
    }

    @GetMapping("/test")
    String test (){
        return "test";
    }


}

