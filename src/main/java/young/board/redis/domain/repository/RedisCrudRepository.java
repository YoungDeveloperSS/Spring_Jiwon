package young.board.redis.domain.repository;

import org.springframework.data.repository.CrudRepository;
import young.board.redis.domain.RedisCrudByRepository;

public interface RedisCrudRepository extends CrudRepository<RedisCrudByRepository, Long> {
}
