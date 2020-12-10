package liu.hope.study.es;

import liu.hope.study.mp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IUserRepository extends CrudRepository<User, Long> {
    /**
     * 保存给定的实体
     * @param entity
     * @return
     */
    User save(User entity);

    /**
     * 	返回由给定ID标识的实体
     * @param primaryKey
     * @return
     */
    Optional<User> findById(Long primaryKey);

    /**
     * 返回所有实体
     * @return
     */
    Iterable<User> findAll();

    /**
     * 返回实体数
     * @return
     */
    long count();

    /**
     * 删除给定的实体
     * @param entity
     */
    void delete(User entity);

    /**
     * 指示是否存在具有给定ID的实体
     * @param primaryKey
     * @return
     */
    boolean existsById(Long primaryKey);

    Iterable<User> findAll(Sort sort);

    Page<User> findAll(Pageable pageable);

    long countByName(String name);

    long deleteByName(String name);

    List<User> removeByName(String name);

    User removeUserById(String id);

    List<User> findByNameStartingWith(String start);

    Stream<User> findDistinctByAgeIsAfterAndName();
}
