package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Document;

import java.util.List;

@Mapper
public interface DocumentMapper {
//
    @Select("""
            select id, time_begin timeBegin, time_end timeEnd, train, title, tag, detail
            from pitchfork.document
            order by id desc
            limit #{take} offset #{skip}
            """)
    List<Document> filter(int skip, int take);

    @Update("""
            update pitchfork.document
            set time_begin = #{timeBegin}
                , time_end = #{timeEnd}
                , train = #{train}
                , title = #{title}
                , tag = #{tag}::jsonb
                , detail = #{detail}::jsonb
            where id = #{id}
            """)
    void update(Document document);

    @Delete("""
            delete from pitchfork.document where id = #{id}
            """)
    void remove(Long id);

    @Select("""
            select id, time_begin timeBegin, time_end timeEnd, train, title, tag, detail
            from pitchfork.document
            where id = #{id}
            """)
    Document filterById(Long id);

    @Insert("""
            insert into pitchfork.document (id, time_begin, time_end, train, title, tag, detail)
                values (#{id}, #{timeBegin}, #{timeEnd}, #{train}, #{title}, #{tag}::jsonb, #{detail}::jsonb)
            """)
    void save(Document data);

}
