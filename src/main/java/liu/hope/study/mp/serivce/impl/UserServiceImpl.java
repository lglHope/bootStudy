package liu.hope.study.mp.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import liu.hope.study.mp.entity.User;
import liu.hope.study.mp.mapper.UserMapper;
import liu.hope.study.mp.serivce.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
