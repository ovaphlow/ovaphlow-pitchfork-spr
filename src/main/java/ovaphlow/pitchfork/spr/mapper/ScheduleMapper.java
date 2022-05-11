package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Schedule;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {

    @Insert("""
            insert into pitchfork.schedule (id, train, time_begin, time_end, dept, detail)
                values (#{id}, #{train}, to_date(#{time_begin},'yyyy-MM-dd hh24:mi:ss'), to_date(#{time_end},'yyyy-MM-dd hh24:mi:ss'),#{dept}, #{detail}::jsonb)
            """)
    void ExcelInsert(List<Schedule> schedule);

    @Select(""" 
            select *, time_begin timeBegin, time_end timeEnd
            from pitchfork.schedule
            """)
    List<Schedule> SearchAll();
}