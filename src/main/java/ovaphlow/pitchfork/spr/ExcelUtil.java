package ovaphlow.pitchfork.spr;

        import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFCell;
        import org.apache.poi.xssf.usermodel.XSSFRow;
        import org.apache.poi.xssf.usermodel.XSSFSheet;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
        import org.springframework.web.multipart.MultipartFile;
        import ovaphlow.pitchfork.spr.entity.User;

        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.text.DecimalFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class ExcelUtil {

    public static List<User> xlsxImportExcel(MultipartFile file) throws IOException {

        XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream()); //获取excel工作簿

        XSSFSheet xssfSheet = xwb.getSheetAt(0); //获取excel的sheet

        if (xssfSheet == null) {
            return null;
        }

        List<User> list = new ArrayList<User>();

        //循环获取excel每一行
        for (int rowNum = 1; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            User user = new User();
            //循环获取excel每一行的每一列
            for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
                XSSFCell xssCell = xssfRow.getCell(cellNum);
                if (xssCell == null) {
                    continue;
                }


                switch (cellNum) {
                    case 0:
                        user.setName(String.valueOf(getXSSFValue(xssCell)));
                        break;
                    case 1:
                        user.setPhone(String.valueOf(getXSSFValue(xssCell)));
                        break;
                }

            }
            list.add(user);  //将excel每一行的数据封装到user对象,并将user对象添加到list
        }
        return list;
    }

    public static Object getXSSFValue(XSSFCell hssfCell) {
        Object result = null;
//        CellType cellType = hssfCell.getCellType();
        result = hssfCell.getStringCellValue();
/*        switch (hssfCell.getCellType()) {
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
        }*/
        return result;
    }


}

