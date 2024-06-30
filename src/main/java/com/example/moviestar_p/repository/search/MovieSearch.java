package com.example.moviestar_p.repository.search;

import com.example.moviestar.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieSearch {
    Page<Movie> search(Pageable pageable);
    Page<Movie> searchAll(String[] types, String keyword, Pageable pageable);
    //Page<MovieListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
