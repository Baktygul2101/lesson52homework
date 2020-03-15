package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    public Iterable<Movie> findAll(Sort s);

    public Iterable<Movie> findAllByReleaseYearGreaterThanEqual(int year, Sort s);

    public Iterable<Movie> findAllByReleaseYearBetween(int year, int year2, Sort s);

    @Query("{'releaseYear' : { '$gte' : ?0, '$lte' : ?1 }}")
    public Iterable<Movie> getMoviesBetween(int year, int year2, Sort s);

    public Movie findByTitle(String title);
    public Movie findByDirectors(String directors);

    @Query ("{'rating': ?0}")
    public List<Movie> selectMoviesWithRating(double howManyStars);

    @Query("{'title': {'$regex' : '?0', '$options' : 'i' }, '$or': [{'releaseYear' : {'$gte' : ?1}}, {'rating' : {'$gte' : ?2}}]}")
    public List<Movie> selectMovies(String title, int year, int rating);


    public Page<Movie> findAllBy(Pageable p);
}
