package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Setting;

import java.util.List;

@Mapper
public interface SettingMapper {
    @Select("select id, ref_id refId, ref1_id ref1Id, tag, detail from pitchfork.setting where id = #{id}")
    Setting filterById(Long id);

    @Update("""
            update pitchfork.setting
            set ref_id = #{refId}
                , ref1_id = #{ref1Id}
                , tag = #{tag}::jsonb
                , detail = #{detail}::jsonb
            where id = #{id}
            """)
    void update(Setting setting);

    @Delete("delete from pitchfork.setting where id = #{id}")
    void remove(Long id);

    @Select("""
            select id, ref_id refId, ref1_id ref1Id, tag, detail
            from pitchfork.setting
            order by id desc
            limit #{take} offset #{skip}
            """)
    List<Setting> filter(Long take, Long skip);

    @Select("""
            select id, ref_id refId, ref1_id ref1Id, tag, detail
            from pitchfork.setting
            where ref_id = #{refId}
            order by id desc
            """)
    List<Setting> filterByRefId(Setting setting);

    @Insert("""
            insert into pitchfork.setting (ref_id, ref1_id, tag, detail)
                values (#{refId}, #{ref1Id}, #{tag}::jsonb, #{detail}::jsonb)
            """)
    void save(Setting setting);
}
