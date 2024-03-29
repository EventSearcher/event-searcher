package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer>{
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}