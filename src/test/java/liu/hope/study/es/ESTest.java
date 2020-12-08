package liu.hope.study.es;

import liu.hope.study.mp.entity.User;
import org.elasticsearch.client.ElasticsearchClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    IUserRepository iUserRepository;


    @Test
    public void addIndexTest() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
//        indexOperations.create();
        indexOperations.createMapping();
    }

    @Test
    public void deleteIndexTest() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
        indexOperations.delete();
    }

    @Test
    public void addDocumentTest() {
        User user = new User(2L , "xiaoming", 18, "lglhope1995@163.com");
        iUserRepository.save(user);
    }

    @Test
    public void getAll() {
//        System.out.println(iUserRepository.findById(2L).get());
        iUserRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(u -> System.out.println(u));
    }

}
