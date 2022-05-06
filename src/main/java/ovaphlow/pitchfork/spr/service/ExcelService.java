/**
 * 业务层
 */
package ovaphlow.pitchfork.spr.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    /**
     * todo：
     * 作用
     * @param file
     */
    void parseExcel(MultipartFile file);
}
