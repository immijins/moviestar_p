package com.example.moviestar_p.repository;

import com.example.moviestar.domain.Movie;
import com.example.moviestar.repository.search.MovieSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieSearch {

}
