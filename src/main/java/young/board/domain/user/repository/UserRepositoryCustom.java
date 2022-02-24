package young.board.domain.user.repository;

import young.board.domain.user.User;

public interface UserRepositoryCustom {
    User findByUserId(Long user_id);
}
