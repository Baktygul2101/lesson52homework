package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Review;
import kz.attractorschool.moviereviewrr.model.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
    public List<Review> findAll(Sort sort);
    public List<Review> findByMovie_Id(String id);
    public List<Review> findByMovie_Directors(String id);
    public Page<Review> findAllBy(Pageable pageable);
   // public List<Review> findByReviewerAndMovie(User name, String id);
  //  public List<Review> findByReviewerAndId(User name);

}
