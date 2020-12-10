package liu.hope.study.es;

import com.google.common.collect.Lists;
import liu.hope.study.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    IUserRepository iUserRepository;

    private static final char[] CHARS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};

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
//        User user = new User(1L , "xiaoming", 18, "lglhope1995@163.com");
//        iUserRepository.save(user);
        // 批量新增
        // 随机生成一个user
        List<User> users = Lists.newArrayListWithCapacity(100);
        for (int i = 0; i < 100; i++) {
            users.add(generateUser(i));
        }
        iUserRepository.saveAll(users);
    }

    @Test
    public void deleteDocumentTest() {
        iUserRepository.removeUserById("2");
    }

    @Test
    public void getAll() {
//        System.out.println(iUserRepository.findById(2L).get());
        iUserRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(System.out::println);
    }

    @Test
    public void testBaseQuery() {
        iUserRepository.findByNameStartingWith("t").forEach(System.out::println);
    }

    public User generateUser(long id) {
        // 取一个随机数
        int age = (int) (1 + Math.random() * 20);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < age; i++) {
            stringBuilder.append(CHARS[(int) (1 + Math.random() * 20)]);
        }
        String name = stringBuilder.toString();
        return new User(id, name, age, name + ".com");
    }

}
