package io.github.ktrzaskoma.bmijava.repo;

import io.github.ktrzaskoma.bmijava.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stats, Long> {

}
