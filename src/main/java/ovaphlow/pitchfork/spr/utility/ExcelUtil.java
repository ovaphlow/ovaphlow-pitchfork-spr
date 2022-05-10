package ovaphlow.pitchfork.spr.utility;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import ovaphlow.pitchfork.spr.entity.Schedule;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<Schedule> xlsxImportExcel(MultipartFile file) throws IOException {
        XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream()); //获取excel工作簿
        XSSFSheet xssfSheet = xwb.getSheetAt(0); //获取excel的sheet
        if (xssfSheet == null) {
            return null;
        }
        List<Schedule> list = new ArrayList<Schedule>();
        //循环获取excel每一行
        for (int rowNum = 1; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            Schedule schedule = new Schedule();
            //循环获取excel每一行的每一列
            for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
                XSSFCell xssCell = xssfRow.getCell(cellNum);
                if (xssCell == null) {
                    continue;
                }
                switch (cellNum) {
                    case 0:
                        schedule.setId(Long.parseLong((String.valueOf(getXSSFValue(xssCell)))));
                        break;
                    case 1:
                        schedule.setTrain(String.valueOf(getXSSFValue(xssCell)));
                        break;
                    case 2:
                        schedule.setTimeBegin(String.valueOf(getXSSFValue(xssCell)));
                        break;
                    case 3:
                        schedule.setTimeEnd(String.valueOf(getXSSFValue(xssCell)));
                        break;
                    case 4:
                        schedule.setDept(String.valueOf(getXSSFValue(xssCell)));
                        break;
                    case 5:
                        schedule.setDetail(String.valueOf(getXSSFValue(xssCell)));
                        break;
                }
            }
            list.add(schedule);  //将excel每一行的数据封装到user对象,并将user对象添加到list
        }
        return list;
    }

    public static Object getXSSFValue(XSSFCell hssfCell) {
        Object result = null;
        switch (hssfCell.getCellType()) {
            case NUMERIC: //数字
                result = hssfCell.getNumericCellValue();
                break;
            case BOOLEAN: //Boolean
                result = hssfCell.getBooleanCellValue();
                break;
            case ERROR: //故障
                result = hssfCell.getErrorCellValue();
                break;
            case FORMULA: //公式
                result = hssfCell.getCellFormula();
                break;
            case BLANK: //空值
                result = "";
                break;
            default: //字符串
                result = hssfCell.getStringCellValue();
        }
        return result;
    }
}
