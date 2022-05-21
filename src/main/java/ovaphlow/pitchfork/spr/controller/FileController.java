package ovaphlow.pitchfork.spr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.entity.Schedule;
import ovaphlow.pitchfork.spr.service.impl.ExcelServiceImpl;
import ovaphlow.pitchfork.spr.utility.Snowflake;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simple")
public class FileController {

    private final ExcelServiceImpl excelService;
    public FileController(ExcelServiceImpl excelService) {
        this.excelService = excelService;
    }
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) throws IOException {
        String oldName = file.getOriginalFilename();
        String path = "D:\\upload_files\\123456\\";
        String filePath = path + oldName;
        File newFile = new File(filePath);
        if (oldName == null) {
            return ResponseEntity.status(401).build();
        }
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/excel", method = RequestMethod.POST)
    public ResponseEntity<List<Schedule>> excel(@RequestBody MultipartFile file) throws IOException {
        // 调用ExcelService->ScheduleMapper->保存到数据库
        List<Schedule> schedule = excelService.parseExcel(file);
        System.out.println(schedule);

        for (int i = 0; i < schedule.size(); i++) {
            Schedule map = schedule.get(i);
            Snowflake flakeId = new Snowflake(1, 1, 1);
            map.setId(flakeId.nextId());
            excelService.ExcelInsert(map);
        }
        return ResponseEntity.status(200).body(schedule);
    }

    @RequestMapping(path = "/excel/get", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> excel1(@RequestBody MultipartFile file) throws IOException {
        List<Schedule> schedule = excelService.parseExcel(file);
        return ResponseEntity.status(200).body(schedule);
    }

    @RequestMapping(path = "/excel", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> Findexcel(){
        List<Schedule> schedule = excelService.FindExcel();
        // 调用ExcelService->ScheduleMapper->保存到数据库
        return ResponseEntity.status(200).body(schedule);
    }



}
