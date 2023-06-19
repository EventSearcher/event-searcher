package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.DTO.UserDTO;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface UserRepository  extends JpaRepository<UserDTO, Integer>{

    boolean existsByName(String name);
}