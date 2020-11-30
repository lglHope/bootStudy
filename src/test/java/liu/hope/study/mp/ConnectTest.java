package liu.hope.study.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import liu.hope.study.mp.entity.User;
import liu.hope.study.mp.mapper.UserMapper;
import liu.hope.study.mp.serivce.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService iUserService;

    @Test
    public void testSelectAll(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(queryWrapper);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectOne1(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, "1");
        System.out.println(("----- selectOne1 method test ------"));
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectOne2(){
        System.out.println(("----- selectOne2 method test ------"));
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, "1"));
        System.out.println(user);
    }

    @Test
    public void testPage(){
        System.out.println(("----- testPage method test ------"));
        List<User> userList = userMapper.selectPage(new Page<>(1, 2), Wrappers.<User>lambdaQuery().likeRight(User::getAge,"2%").orderByDesc(User::getId)).getRecords();
        Assert.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void insertOne1(){
        User user = new User();
        user.setAge(24);
        user.setName("xiaoming");
        user.setEmail("test6@baomidou.com");
        System.out.println(("----- insertOne method test ------"));
        Assert.assertTrue(iUserService.saveOrUpdate(user));
        System.out.println(user.getId());
    }

    @Test
    public void insertOne2(){
        User user = new User();
        user.setAge(24);
        user.setName("xiaoming");
        user.setEmail("test6@baomidou.com");
        System.out.println(("----- insertOne method test ------"));
        Assert.assertEquals(1, userMapper.insert(user));
        System.out.println(user.getId());
    }

    @Test
    public void mapKeyTest() {
        System.out.println(("----- getUserList method test ------"));
        Map<Long, User> userList = userMapper.getUserList();
//        Assert.assertEquals(5, userList.size());
        userList.forEach((k, v) -> System.out.println(k + "," + v));

        System.out.println(("----- getUser method test ------"));
        Map<String, User> user = userMapper.getUser(Wrappers.<User>lambdaQuery().eq(User::getId, "1"));
//        Assert.assertEquals(1, userList.size());
        System.out.println(user);
    }

}
