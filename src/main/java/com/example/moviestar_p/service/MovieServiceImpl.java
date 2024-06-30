package com.example.moviestar_p.service;

import com.example.moviestar.domain.Movie;
import com.example.moviestar.dto.MovieDTO;
import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;
import com.example.moviestar.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MovieServiceImpl implements MovieService {
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    @Override
    public Long register(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Long no = movieRepository.save(movie).getNo();
        return no;
    }

    @Override
    public PageResponseDTO<MovieDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Movie> moviePage = movieRepository.searchAll(types, keyword, pageable);
        // Page의 제네릭 타입 <Movie>를 변환
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : moviePage.getContent()) {
            movieDTOList.add(modelMapper.map(movie, MovieDTO.class));
        }

        return PageResponseDTO.<MovieDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(movieDTOList)
                .total((int)moviePage.getTotalElements())
                .build();
    }

    @Override
    public MovieDTO readOne(Long no) {
        Optional<Movie> optionalMovie = movieRepository.findById(no);
        Movie movie = optionalMovie.orElseThrow();
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public void modify(MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieDTO.getNo());
        Movie movie = optionalMovie.orElseThrow();
        movie.change(movieDTO.getTitle(), movieDTO.getContent(), movieDTO.getImagePath());
        movieRepository.save(movie);
    }

    @Override
    public void remove(Long no) {
        movieRepository.deleteById(no);
    }
}
