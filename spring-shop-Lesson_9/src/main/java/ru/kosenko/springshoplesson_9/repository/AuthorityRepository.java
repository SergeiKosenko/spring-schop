package ru.kosenko.springshoplesson_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kosenko.springshoplesson_9.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

  Authority findByName(String name);
}
