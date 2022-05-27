/**
 * 控制层
 */
package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.Schedule;
import ovaphlow.pitchfork.spr.entity.Setting;
import ovaphlow.pitchfork.spr.mapper.ScheduleMapper;
import ovaphlow.pitchfork.spr.utility.RedisUtil;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.mapper.DocumentMapper;
import ovaphlow.pitchfork.spr.utility.Snowflake;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/simple/biz")
public class ScheduleController {

    private final ScheduleMapper scheduleMapper;
    private Object request;

    public ScheduleController(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(path = "/schedule/q", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> filter(@RequestParam(value = "option", defaultValue = "") String option,
                                               HttpServletRequest request) {
        if ("filter-timeRange".equals(option)) {
            String timeBegin = request.getParameter("timeBegin");
            String timeEnd = request.getParameter("timeEnd");
            List<Schedule> scheduleList = scheduleMapper.filterBySearch(timeBegin,timeEnd);
            return ResponseEntity.status(200).body(scheduleList);
        }
        return ResponseEntity.status(200).body(new ArrayList<>());
    }

    @RequestMapping(path = "/schedule", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> q(@RequestParam(value = "option", defaultValue = "") String option) {
        if ("jx".equals(option)) {
            List<Schedule> result = scheduleMapper.jx();
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).build();
    }
}





