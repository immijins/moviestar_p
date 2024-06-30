package com.example.moviestar_p.repository.search;

import com.example.moviestar.domain.Movie;
import com.example.moviestar.domain.QMovie;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MovieSearchImpl extends QuerydslRepositorySupport implements MovieSearch {
    public MovieSearchImpl() {super(Movie.class);}

    @Override
    public Page<Movie> search(Pageable pageable) {
        QMovie movie = QMovie.movie; // Q도메인 객체
        JPQLQuery<Movie> query = from(movie); // select ... from movie

        // 검색 조건 (where 조건 강화)
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(movie.title.contains("11")); // title like
        booleanBuilder.or(movie.content.contains("11")); // content like

        query.where(booleanBuilder);
        query.where(movie.no.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Movie> list = query.fetch();
        long count = query.fetchCount();

        return  null;
    }

    @Override
    public Page<Movie> searchAll(String[] types, String keyword, Pageable pageable) {
        QMovie movie = QMovie.movie;
        JPQLQuery<Movie> query = from(movie);

        if ((types != null && types.length > 0) && keyword != null) { // 검색 조건
            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
            for (String type: types) {
                switch (type) {
                    case "t" :
                        booleanBuilder.or(movie.title.contains(keyword));
                        break;
                    case "c" :
                        booleanBuilder.or(movie.content.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // no > 0
        query.where(movie.no.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Movie> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    /*
    @Override
    public Page<MovieListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QMovie movie = QMovie.movie;


        return null;
    } */
}
