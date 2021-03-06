/**
 * 业务层
 */
package ovaphlow.pitchfork.spr.service;

import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.entity.Schedule;

import java.io.IOException;
import java.util.List;

public interface ExcelService {

    /**
     * todo：
     * 作用
     * @param file
     * @return
     */

    List<Schedule> parseExcel(MultipartFile file) throws IOException;
    List<Schedule> FindExcel();
    void ExcelInsert(Schedule schedule);
}
