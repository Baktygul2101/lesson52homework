package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.MovieReviewrrApplication;
import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import kz.attractorschool.moviereviewrr.model.User;
import kz.attractorschool.moviereviewrr.repository.MovieRepository;
import kz.attractorschool.moviereviewrr.repository.ReviewRepository;
import kz.attractorschool.moviereviewrr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    MovieRepository mr;
    @Autowired
    ReviewRepository rr;
    @Autowired
    UserRepository ur;

    @GetMapping("/movies")
    public Iterable<Movie> getMovie() {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAll(s);
    }
    @GetMapping("/movieby/{year}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year) {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAllByReleaseYearGreaterThanEqual(year, s);
    }
    @GetMapping("/movienew/{year}/{year2}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year,
                                    @PathVariable("year2") int year2) {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.findAllByReleaseYearBetween(year, year2, s);
    }

    @GetMapping("/moviebetween/{year}/{year2}")
    public Iterable<Movie> getMovieBetween(@PathVariable("year") int year,
                                           @PathVariable("year2") int year2) {
        Sort s = Sort.by(Sort.Order.asc("title"));
        return mr.getMoviesBetween(year, year2, s);
    }

    @GetMapping("/reviews")
    public List<Review> getReview() {
        Sort sort = Sort.by(Sort.Order.asc("review"));
        return rr.findAll(sort);
    }

    @GetMapping("/reviewbymovie/{title}")
    public List<Review> getMovie(@PathVariable("title") String title) {
        return rr.findByMovie_Id(mr.findByTitle(title).getId());
    }
    @GetMapping("/reviewbydirector/{directors}")
    public List<Review> getMovieD(@PathVariable("directors") String directors) {
        return rr.findByMovie_Directors(mr.findByDirectors(directors).getId());
    }
    @GetMapping("/movie/{title}")
    public Movie getMovieOne(@PathVariable("title") String title) {
        return mr.findByTitle(title);
    }
    @GetMapping("/movierating/{rating}")
    public List<Movie> getMovie(@PathVariable("rating") double rating){
        return mr.selectMoviesWithRating(rating);
    }
    @GetMapping("/film/{title}/{year}/{rating}")
    public List<Movie> getMovieWhich(@PathVariable("title") String title,
                                     @PathVariable("year") int year,
                                     @PathVariable("rating") int rating) {
        return mr.selectMovies(title, year, rating);
    }
    @GetMapping("/films")
    public Page<Review> getReviewPage(){
        Sort sortBy = Sort.by(Sort.Order.asc("stars"));
        int page = 1;
        int count = 3;
        Pageable pageable = PageRequest.of(page, count,sortBy);
        return rr.findAllBy(pageable);
    }

    @GetMapping("/filmpage")
    public Page<Movie> getMoviePage(){
        Sort sBy = Sort.by(Sort.Order.asc("title"),Sort.Order.asc("rating"));
        int page = 1;
        int count = 5;
        Pageable p = PageRequest.of(page, count);
        return mr.findAllBy(p);
    }

}
