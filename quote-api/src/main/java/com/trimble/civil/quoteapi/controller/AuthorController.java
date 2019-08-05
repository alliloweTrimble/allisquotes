package com.trimble.civil.quoteapi.controller;

import com.trimble.civil.quoteapi.author.Author;
import com.trimble.civil.quoteapi.author.AuthorRepository;
import com.trimble.civil.quoteapi.quotes.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    // Get All Authors paged
    @GetMapping(path = "/authors")
    Page<Author> loadAuthors(@PageableDefault(page = 0, size = 17)
                                   Pageable pageable) {

        return authorRepository.findAll(pageable);
    }

    // Get All Authors by searching for a name paged
    @GetMapping( path = "/authors", params = "name")
    Page<Author> findByAuthorName(String name, Pageable pageable) {

        return authorRepository.findAllByNameContains(name, pageable);
    }

}
