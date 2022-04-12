package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Document;
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

    @Select("select * from pitchfork.user where id = #{id}")
    User filterById(Long id);

    @Insert("""
            insert into pitchfork.user (name, phone, dept, password, salt, tag)
                values (#{name}, #{phone}, #{dept}, #{password}, #{salt}, #{tag}::jsonb)
            """)
    void save(User user);

    @Update("""
            update pitchfork.user
            set name = #{name}
                , password = #{password}
                , phone = #{phone}
                , dept = #{dept}
                , tag = #{tag}::jsonb
            where id = #{id}
            """)
    void update(User user);

    @Delete("""
            delete from pitchfork.user where id = #{id}
            """)
    void remove(Long id);

    @Select("""
            select * from pitchfork.user where name = #{name}
            """)
    List<User> filterByName(String name);

}
