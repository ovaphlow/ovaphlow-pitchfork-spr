/**
 * 控制层
 */
package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class DocumentController {

    private final DocumentMapper documentMapper;
    public DocumentController(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/document/stats", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> stats(@RequestParam(value = "option", defaultValue = "") String option) {
        if ("statsCountTotal".equals(option)) {
            Map<String, Object> result = documentMapper.statsCountTotal();
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document/{id}/{title}", method = RequestMethod.GET)
    public ResponseEntity<Document> getByIdTitle(@PathVariable("id") Long id, @PathVariable("title") String title) {
        Document result = documentMapper.filterByIdTitle(id, title);
        return ResponseEntity.status(200).body(result);
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.GET)
    public ResponseEntity<Document> getById(@PathVariable("id") Long id) {
        Document document;
        String key = "document" + id;
        if (redisUtil.hasKey(key)) {
            document = (Document) redisUtil.get(key);
        } else {
            document = documentMapper.filterById(id);
            redisUtil.set(key, document);
        }
        return ResponseEntity.status(200).body(document);
    }

    // 技术员审核 需要前端提交 审核结果 班组 质检 审核状态（技术员审核） 技术员id 姓名 审核时间
    @RequestMapping(path = "/document/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Document> update(@PathVariable("id") Long id, @RequestBody Document body) {
        body.setId(id);
        documentMapper.update(body);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Document> remove(@PathVariable("id") Long id) {
        documentMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> filter(@RequestParam(value = "option", defaultValue = "") String option,
                                                 @RequestParam(value = "skip", defaultValue = "0") int skip,
                                                 @RequestParam(value = "take", defaultValue = "10") int take,
                                                 HttpServletRequest request) {
        if ("".equals(option)) {
            List<Document> result = documentMapper.filter(skip, take);
            return ResponseEntity.status(200).body(result);
        }
        if ("filterBy-timeRange-train".equals(option)) {
            String train = request.getParameter("train");
            String timeBegin = request.getParameter("timeBegin");
            String timeEnd = request.getParameter("timeEnd");
            List<Document> result = documentMapper.filterByTimeRangeTrain(timeBegin, timeEnd, train);
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document", method = RequestMethod.POST)
    public ResponseEntity<Document> save(@RequestBody Document body) {
        Snowflake flakeId = new Snowflake(1, 1, 1);
        body.setId(flakeId.nextId());
        documentMapper.save(body);
        return ResponseEntity.status(201).build();
    }

    @RequestMapping(path = "/document/today", method = RequestMethod.GET)
    public ResponseEntity<Long> CountNumberForDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cd.add(Calendar.DATE, 1);
        String dateString2 = sdf.format(cd.getTime());
        Long CountNumber = documentMapper.CountDataForDay(dateString, dateString2);
        return ResponseEntity.status(200).body(CountNumber);
    }

    @RequestMapping(path = "/document/all", method = RequestMethod.GET)
    public ResponseEntity<Long> CountNumberForAll() {
        Long CountNumber = documentMapper.CountDataForAll();
        return ResponseEntity.status(200).body(CountNumber);
    }

    @RequestMapping(path = "/document/working", method = RequestMethod.GET)
    public ResponseEntity<Long> CountNumberForWorking() {
        Long CountNumber = documentMapper.CountDataForWorking();
        return ResponseEntity.status(200).body(CountNumber);
    }

    @RequestMapping(path = "/document/train", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> CountNumberFortrain() {
        List<Map<String, Object>> Train = documentMapper.CountNumberForTrain();
        return ResponseEntity.status(200).body(Train);
    }

    @RequestMapping(path = "/document/percent", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> CountPercent() {
        float Count2 = documentMapper.CountPercent2(); //计划外作业
        float Count3 = documentMapper.CountPercent3(); //计划内作业
        float CountAll  =(Count2 + Count3);
        Long Precent2 =(long)(Count2/CountAll*100);
        Long Precent3 =(long)(Count3/CountAll*100);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("总数",CountAll);
        map.put("计划外作业占比",Precent2);
        map.put("计划内作业占比",Precent3);
        return ResponseEntity.status(200).body(map);
    }

}
