package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public Iterable<User> findAll(Sort sort);
    public User findByEmail(String email);
    public User findByName(String name);
    boolean existsByEmail(String email);
}
