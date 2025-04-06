package gabriel.infra.database.repository;

import gabriel.infra.database.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {
}
