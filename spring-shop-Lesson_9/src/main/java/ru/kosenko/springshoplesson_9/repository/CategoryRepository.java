package ru.kosenko.springshoplesson_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosenko.springshoplesson_9.model.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByTitle(String title);
}
