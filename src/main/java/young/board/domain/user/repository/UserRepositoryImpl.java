package young.board.domain.user.repository;

import lombok.RequiredArgsConstructor;
import young.board.domain.user.User;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final EntityManager em;

    @Override
    public User findByUserId(Long user_id) {
        return em.find(User.class,user_id);
    }
}
