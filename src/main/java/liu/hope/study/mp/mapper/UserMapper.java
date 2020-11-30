package liu.hope.study.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import liu.hope.study.mp.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    @Select("select * from user")
    Map<Long, User> getUserList();

    @MapKey("id")
    @Select("select * from user ${ew.customSqlSegment}")
    Map<String, User> getUser(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}