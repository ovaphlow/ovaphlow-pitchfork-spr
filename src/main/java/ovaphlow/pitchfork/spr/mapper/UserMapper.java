package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ovaphlow.pitchfork.spr.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("""
            select *
            from pitchfork.user
            order by id desc
            limit #{take} offset #{skip}
            """)
    List<User> filter(Long take, Long skip);

    @Insert("""
            insert into pitchfork.user (name, phone, dept, dept_id, password, salt, tag)
                values (#{name}, #{phone}, #{dept}, #{deptId}, #{password}, #{salt}, #{tag}::jsonb)
            """)
    void save(User user);
}
