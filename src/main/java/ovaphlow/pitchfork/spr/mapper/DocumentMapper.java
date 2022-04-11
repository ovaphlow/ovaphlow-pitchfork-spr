package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Document;

import java.util.List;

@Mapper
public interface DocumentMapper {
//
    @Select("""
            select * from pitchfork.document order by id desc limit #{take} offset #{skip}
            """)
    List<Document> filter(int skip, int take);

    @Update("""
            update pitchfork.document 
            set title = #{title},dept = #{dept},timeBegin = #{timeBegin},timeEnd = #{timeEnd}
            where id = #{id}
            """)
    void update(Document document);

    @Delete("""
            delete from pitchfork.document where id = #{id}
            """)
    void remove(Long id);

    @Select("""
            select * from pitchfork.document where id = #{id}
            """)
    Document filterById(Long id);

    @Insert("""
            insert into pitchfork.document (title,dept,timeBegin,timeEnd)
                values (#{title},#{dept},#{timeBegin},#{timeEnd})
            """)
    void save(Document data);

}
