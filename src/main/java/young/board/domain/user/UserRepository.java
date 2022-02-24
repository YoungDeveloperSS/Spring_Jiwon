package young.board.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import young.board.domain.user.repository.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}

