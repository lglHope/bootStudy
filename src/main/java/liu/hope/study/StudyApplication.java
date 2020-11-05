package liu.hope.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("liu.hope.study.mp.mapper")
public class StudyApplication {

    public static void main(String[] args) {
        // https://baomidou.com/guide/
        SpringApplication.run(StudyApplication.class, args);
    }

}
