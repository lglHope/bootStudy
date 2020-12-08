package liu.hope.study.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Document(indexName = "user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Keyword, index = false)
    private Integer age;
    @Field(type = FieldType.Keyword)
    private String email;
}