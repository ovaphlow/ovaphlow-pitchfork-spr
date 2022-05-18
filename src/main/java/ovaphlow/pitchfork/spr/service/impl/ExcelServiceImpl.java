package ovaphlow.pitchfork.spr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.entity.Schedule;
import ovaphlow.pitchfork.spr.mapper.ScheduleMapper;
import ovaphlow.pitchfork.spr.service.ExcelService;
import ovaphlow.pitchfork.spr.utility.ExcelUtil;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Override
    public List<Schedule> parseExcel(MultipartFile file) throws IOException {
        return ExcelUtil.xlsxImportExcel(file);
    }

    @Override
    public void ExcelInsert(Schedule schedule){
        scheduleMapper.ExcelInsert(schedule);
    }

    @Override
    public List<Schedule> FindExcel(){
        return scheduleMapper.SearchAll();
    }

}
