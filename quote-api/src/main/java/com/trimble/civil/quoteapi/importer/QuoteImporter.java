package com.trimble.civil.quoteapi.importer;

import com.trimble.civil.quoteapi.author.Author;
import com.trimble.civil.quoteapi.author.AuthorRepository;
import com.trimble.civil.quoteapi.quotes.Quote;
import com.trimble.civil.quoteapi.quotes.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@SpringBootApplication
public class QuoteImporter implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    public QuoteImporter(
            AuthorRepository authorRepository,
            QuoteRepository quoteRepository){
        this.authorRepository = authorRepository;
        this.quoteRepository = quoteRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        log.debug("Started importer");
        File file = ResourceUtils.getFile("classpath:author-quote.txt");

        System.out.println("File Found : " + file.exists());

        List<String> authorAndQuote = Files.readAllLines(file.toPath());

        authorAndQuote.forEach(aq -> {

            String[] aqParts = aq.split("\t");

            String authorName = aqParts[0];
            String quoteText = aqParts[1];

            log.debug("Find Author with name: {}", authorName);
            Author author = authorRepository
                    .findByName(authorName)
                    .orElseGet(() -> {
                        log.debug("Creating Author with name :'{}'", authorName);
                        return authorRepository.save(new Author(0L, authorName));
                    });

            log.debug("Creating Quote with Author '{}' and Text '{}'", author, quoteText);
            Quote quote = new Quote(0L, author, quoteText);

            quoteRepository.save(quote);
        });
        log.debug("Importer finished");
    }

//    @Override
//    public List<Quote> findById(Long authorId, Pageable pageable) {
//        String author = authorRepository.findAllByNameContains(name, pageable);
//        List<Quote> result = quoteRepository.findAllByAuthorId(authorId, pageable);
//        return result
//    }

}
