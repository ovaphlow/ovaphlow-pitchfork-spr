package ovaphlow.pitchfork.spr.mapper;

import org.apache.ibatis.annotations.*;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.entity.Schedule;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {
    @Select("""
            select * from pitchfork.schedule
            """)
    List<Schedule> jx();

    @Insert("""
            insert into pitchfork.schedule (id, train, time_begin, time_end, dept, detail)
                values (#{id}, #{train}, to_date(#{timeBegin},'yyyy-MM-dd hh24:mi:ss'), to_date(#{timeEnd},'yyyy-MM-dd hh24:mi:ss'), #{dept}, #{detail}::jsonb)
            """)
    void ExcelInsert(Schedule schedule);

    @Select(""" 
            select *, time_begin timeBegin, time_end timeEnd
            from pitchfork.schedule
            """)
    List<Schedule> SearchAll();
    @Select("""
            select count(*) ,time_begin timeBegin , time_end timeEnd from pitchfork.schedule
            where time_begin between #{timeBegin} and #{timeEnd}
            """)
    List<Schedule> filterBySearch(String timeBegin, String timeEnd);
}