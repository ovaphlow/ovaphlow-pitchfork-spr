package ovaphlow.pitchfork.spr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void parseExcel(MultipartFile file) {
        System.out.println("11");
    }
}
