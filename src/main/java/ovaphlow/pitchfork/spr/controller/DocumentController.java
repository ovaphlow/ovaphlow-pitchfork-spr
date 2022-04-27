package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.RedisUtil;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.mapper.DocumentMapper;
import ovaphlow.pitchfork.spr.utility.Snowflake;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/simple/biz")
public class DocumentController {

    Document document;

    @Autowired
    private RedisUtil redisUtil;

    private final DocumentMapper documentMapper;

    public DocumentController(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @RequestMapping(path = "/document/{id}/{title}", method = RequestMethod.GET)
    public ResponseEntity<Document> getByIdTitle(@PathVariable("id") Long id,@PathVariable("title") String title){
        Document result = documentMapper.filterByIdTitle(id,title);
        return ResponseEntity.status(200).body(result);
    }



    @RequestMapping(path = "/document/{id}", method = RequestMethod.GET)
    @Cacheable(value = "Document" , key = "#root.method.name")
    public ResponseEntity<Document> getById(@PathVariable("id") Long id) {
        Document result = documentMapper.filterById(id);



        String key = "document"+id;
        if(redisUtil.hasKey(key)) {
            document = (Document)redisUtil.get(key);
            System.out.println("search from cache");
        }else{
            document = documentMapper.filterById(id);
            System.out.println("search from database");
            System.out.println(redisUtil.set(key,document) ? "success insert" : "error insert");
        }


        return ResponseEntity.status(200).body(result);


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
            List<Document> result = documentMapper.filterByTimeRangeTrain(timeBegin,timeEnd,train);
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


    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(@RequestBody MultipartFile file) throws IOException {

        String oldName=file.getOriginalFilename();
        String path="D:\\upload_files\\";
        String filePath=path+oldName;
        File newFile=new File(filePath);
        file.transferTo(newFile);
        return filePath;
        
    }

}
