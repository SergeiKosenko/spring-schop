package ru.kosenko.springshoplesson_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kosenko.springshoplesson_9.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("select p from Product p left join fetch p.category")
  List<Product> findAll();
}
