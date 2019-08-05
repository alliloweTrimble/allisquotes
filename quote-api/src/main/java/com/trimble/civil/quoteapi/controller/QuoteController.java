package com.trimble.civil.quoteapi.controller;

import com.trimble.civil.quoteapi.author.Author;
import com.trimble.civil.quoteapi.author.AuthorRepository;
import com.trimble.civil.quoteapi.exception.QuoteNotFoundException;
import com.trimble.civil.quoteapi.importer.QuoteImporter;
import com.trimble.civil.quoteapi.quotes.Quote;
import com.trimble.civil.quoteapi.quotes.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.Streamable;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlType;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class QuoteController {


    private final QuoteRepository repository;
    private final AuthorRepository authorRepository;

    QuoteController(QuoteRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    // Get All Quotes paged
    @GetMapping(path = "/quotes")
    Page<Quote> loadQuotes(@PageableDefault(page = 0, size = 17)
                                   Pageable pageable) {

        return repository.findAll(pageable);
    }

    // Get All Quotes that contain a certain word paged
    @GetMapping(path = "/quotes", params = "text")
    Page<Quote> all(String text, Pageable pageable) {

        return repository.findAllByTextContains(text, pageable);
    }

    // Get All Quotes by authorId search, paged
    @GetMapping(path = "/quotes", params = "authorId")
    Page<Quote> allAuthorIdQuotes(Long authorId, Pageable pageable) {

        return repository.findAllByAuthorId(authorId, pageable);
    }

    //Add a new Quote to existing author
    @PostMapping(path = "/quotes")
    Quote newQuote(@RequestBody Quote newQuote) {

        return repository.save(newQuote);
    }

    //Update a quote's text by quoteId
    @PutMapping(path = "/quotes/{id}")
    Quote replaceQuote(@RequestBody Quote newQuote, @PathVariable Long id){

        return repository.findById(id)
                .map(quote -> {
                    quote.setText(newQuote.getText());
                    return repository.save(quote);
                })
                .orElseGet(() -> {
                    newQuote.setId(id);
                    return repository.save(newQuote);
                });
    }


    // Delete a quote by it's id
    @DeleteMapping(path = "/quotes/{id}")
    void deleteQuote(@PathVariable Long id) {
        repository.deleteById(id);
    }


}



