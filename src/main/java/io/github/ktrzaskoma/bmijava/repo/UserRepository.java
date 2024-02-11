package io.github.ktrzaskoma.bmijava.repo;

import io.github.ktrzaskoma.bmijava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
