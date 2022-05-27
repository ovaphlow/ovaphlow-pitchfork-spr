/**
 * 控制层
 */
package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ovaphlow.pitchfork.spr.entity.Schedule;
import ovaphlow.pitchfork.spr.mapper.ScheduleMapper;
import ovaphlow.pitchfork.spr.utility.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simple/biz")
public class ScheduleController {

    private final ScheduleMapper scheduleMapper;
    private Object request;
    @Autowired
    private RedisUtil redisUtil;

    public ScheduleController(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @RequestMapping(path = "/schedule/q", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> filter(
            @RequestParam(value = "option", defaultValue = "") String option,
            HttpServletRequest request
    ) {
        if ("filterBy-timeRange".equals(option)) {
            String date = request.getParameter("date");
            Map<String, Object> result = scheduleMapper.filterBySearch(date);
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).body(Map.of("qty", 0));
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
