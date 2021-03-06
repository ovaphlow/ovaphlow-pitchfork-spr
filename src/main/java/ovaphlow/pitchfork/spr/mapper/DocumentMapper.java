package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Document;

import java.util.List;
import java.util.Map;

@Mapper
public interface DocumentMapper {
    @Select("""
        select count(*) qwer
        from pitchfork.document
        where time_begin like #{date}
        """)
    Map<String, Object> Search(String date);

    @Select("""
            select count(*) from pitchfork.document
            where time_begin between '2022-05-16' and '2022-05-17'
            """)
    Map<String, Object> lianxi();

    @Select("""
            select count(*) from pitchfork.document
            """)
    Map<String, Object> statsCountTotal();

    @Select("""
            select id, time_begin timeBegin, time_end timeEnd, train, title, tag, detail, approve, review, sub01, sub02, sub03, sub04
            from pitchfork.document
            order by id desc
            limit #{take} offset #{skip}
            """)
    List<Document> filter(int skip, int take);

    @Select("""
            select *,time_begin timeBegin,time_end timeEnd from pitchfork.document
            where time_begin between #{timeBegin} and #{timeEnd} and train = #{train}
            order by id desc
            """)
    List<Document> filterByTimeRangeTrain(String timeBegin, String timeEnd, String train);

    @Update("""
            update pitchfork.document
            set time_begin = #{timeBegin}
                , time_end = #{timeEnd}
                , train = #{train}
                , title = #{title}
                , tag = #{tag}::jsonb
                , detail = #{detail}::jsonb
                , approve = #{approve}::jsonb
                , review = #{review}::jsonb
                , sub01 = #{sub01}::jsonb
                , sub02 = #{sub02}::jsonb
                , sub03 = #{sub03}::jsonb
                , sub04 = #{sub04}::jsonb
            where id = #{id}
            """)
    void update(Document document);

    @Delete("""
            delete from pitchfork.document where id = #{id}
            """)
    void remove(Long id);

    @Select("""
            select id, time_begin timeBegin, time_end timeEnd, train, title, tag, detail, approve, review, sub01, sub02, sub03, sub04
            from pitchfork.document
            where id = #{id}
            """)
    Document filterById(Long id);

    @Insert("""
            insert into pitchfork.document (
                id
                , time_begin
                , time_end
                , train
                , title
                , tag
                , detail
                , approve
                , review
                , sub01
                , sub02
                , sub03
                , sub04
            ) values (
                #{id}
                , #{timeBegin}
                , #{timeEnd}
                , #{train}
                , #{title}
                , #{tag}::jsonb
                , #{detail}::jsonb
                , #{approve}::jsonb
                , #{review}::jsonb
                , #{sub01}::jsonb
                , #{sub02}::jsonb
                , #{sub03}::jsonb
                , #{sub04}::jsonb
            )
            """)
    void save(Document data);

    @Select("""
            select *, time_begin timeBegin, time_end timeEnd from pitchfork.document where id = #{id} and title = #{title}
            """)
    Document filterByIdTitle(Long id, String title);

    @Select("""
            select count(*) qty
            from pitchfork.document
            where time_begin between #{timeBegin} and #{timeEnd}
            """)
    Long CountDataForDay(String timeBegin, String timeEnd);

    @Select("""
            select count(*) from pitchfork.document
            """)
    Long CountDataForAll();

    @Select("""
            select count(*) from pitchfork.document
            where tag @> '["??????"]' = false
            """)
    Long CountDataForWorking();

    @Select("""
            select train, count(train) ??????
            from pitchfork.document
            group by train
            """)
    List<Map<String, Object>> CountNumberForTrain();

    @Select("""
            select count(*)
            from pitchfork.document
            where tag @> '["???????????????"]'
            """)
    Long CountPercent2();

    @Select("""
            select count(*)
            from pitchfork.document
            where tag @> '["???????????????"]'
            """)
    Long CountPercent3();


}
