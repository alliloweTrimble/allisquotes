package com.trimble.civil.quoteapi.quotes;


import com.trimble.civil.quoteapi.quotes.Quote;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findAllByAuthorId(Long authorId, Pageable pageable);

    Page<Quote> findAllByTextContains(String quoteFragment, Pageable pageable);

//    Page<Quote> findByQuoteId(Long quoteId);

}
