package liu.hope.study.es;

import liu.hope.study.mp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.util.Streamable;

import java.util.List;

// https://docs.spring.io/spring-data/elasticsearch/docs/4.0.5.RELEASE/reference/html/#
@NoRepositoryBean
public interface IUser2Repository extends CrudRepository<User, Long> {

    // 表达式通常是属性遍历，并带有可串联的运算符   AND  OR   Between   LessThan  GreaterThan   Like

    // 单个属性  IgnoreCase   AllIgnoreCase

    // OrderBy子句   Asc   Desc

    // 属性是个类时,去期类中的字段 用下换线分割 例:findByAddress_ZipCode

    List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);

    // Enables the distinct flag for the query
    List<User> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
    List<User> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

    // Enabling ignoring case for an individual property
    List<User> findByLastnameIgnoreCase(String lastname);
    // Enabling ignoring case for all suitable properties
    List<User> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    // Enabling static ORDER BY for a query
    List<User> findByLastnameOrderByFirstnameAsc(String lastname);
    List<User> findByLastnameOrderByFirstnameDesc(String lastname);

    // 接受Sort并Pageable期望将非null值传递给方法的API 。如果您不想应用任何排序或分页，请使用Sort.unsorted()和Pageable.unpaged()

//    Page<User> findByLastname(String lastname, Pageable pageable);
//
//    Slice<User> findByLastname(String lastname, Pageable pageable);

    List<User> findByLastname(String lastname, Sort sort);

    List<User> findByLastname(String lastname, Pageable pageable);

    Streamable<User> findByFirstnameContaining(String firstname);

    Streamable<User> findByLastnameContaining(String lastname);
}
