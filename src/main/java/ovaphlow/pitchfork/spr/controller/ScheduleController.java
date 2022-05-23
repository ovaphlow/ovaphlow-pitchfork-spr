/**
 * 控制层
 */
package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    public ScheduleController(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/schedule/q", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> q(@RequestParam(value = "option", defaultValue = "") String option) {
        if ("xx".equals(option)) {
            Map<String, Object> result = scheduleMapper.xx();
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).build();
    }
}
