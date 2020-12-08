package liu.hope.study.es;

import liu.hope.study.mp.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IUserRepository extends ElasticsearchRepository<User, Long> {
}
