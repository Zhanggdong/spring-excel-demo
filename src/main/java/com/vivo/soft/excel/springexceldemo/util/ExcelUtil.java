package com.vivo.soft.excel.springexceldemo.util;

import com.vivo.soft.excel.springexceldemo.demo.FilePathUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-18.
 * @Time 22:39
 * @Description 解析复杂Excel格式工具类
 * @Version 2.0.0
 */
public class ExcelUtil {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName= "import-复杂格式.xlsx";
        File file = new File(FilePathUtil.DIR+"import-复杂格式.xlsx");
        FileInputStream fis = new FileInputStream(file);
        String result = addReportByExcel(0L,fis,fileName);
    }
    public static String addReportByExcel(Long userId, InputStream inputStream, String fileName)throws BusinessException{
        String message = "Import success";
        boolean isE2007 = false;    //判断是否是excel2007格式
        if(fileName.endsWith("xlsx")){
            isE2007 = true;
        }
        int rowIndex = 0;
        int columnIndex = 0;
        List<InspectionReport> irs = new ArrayList<>();
        try {
            InputStream input = inputStream;  //建立输入流
            Workbook wb  = null;
            //根据文件格式(2003或者2007)来初始化
            if(isE2007){
                wb = new XSSFWorkbook(input);
            }else{
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);    //获得第一个表单
            System.out.println("总行数:"+sheet.getLastRowNum());
            List<CellRangeAddress> cras = getCombineCell(sheet);
            //isMergedRegion(Sheet sheet,int row ,int column);判断是不是合并单元格
            int count = sheet.getLastRowNum()+1;//总行数
            for(int i = 1; i < count;i++){
                rowIndex = i;
                Row row = sheet.getRow(i);
                InspectionReport ir = new InspectionReport();
                ir.setReportName(getCellValue(row.getCell(0)));
                ir.setShift(Double.valueOf(getCellValue(row.getCell(1))).intValue());
                ir.setLine(getCellValue(row.getCell(2)));
                ir.setStationCode(getCellValue(row.getCell(3)));
                ir.setArea(Double.valueOf(getCellValue(row.getCell(4))).intValue());
                ir.setReportStatus(Double.valueOf(getCellValue(row.getCell(5))).intValue());
                List<InspectionItem> items = new ArrayList<>();
                if(isMergedRegion(sheet,i,0)){
                    int lastRow = getRowNum(cras,sheet.getRow(i).getCell(0),sheet);
                    for(;i<=lastRow;i++){
                        row = sheet.getRow(i);
                        InspectionItem item = new InspectionItem();
                        item.setItem(getCellValue(row.getCell(6)));
                        item.setMethod(getCellValue(row.getCell(7)));
                        item.setMode(getCellValue(row.getCell(8)));
                        item.setStandardValue(getCellValue(row.getCell(9)));
                        item.setDeviationValue(getCellValue(row.getCell(10)));
                        String pinci = getCellValue(row.getCell(11));
                        Double d = Double.valueOf(pinci);
                        item.setFrequency(d.intValue());
                        items.add(item);
                    }
                    i--;
                }else {
                    row = sheet.getRow(i);
                    InspectionItem item = new InspectionItem();
                    item.setItem(getCellValue(row.getCell(6)));
                    item.setMethod(getCellValue(row.getCell(7)));
                    item.setMode(getCellValue(row.getCell(8)));
                    item.setStandardValue(getCellValue(row.getCell(9)));
                    item.setDeviationValue(getCellValue(row.getCell(10)));
                    String pinci = getCellValue(row.getCell(11));
                    Double d = Double.valueOf(pinci);
                    item.setFrequency(d.intValue());
                    items.add(item);
                }
                ir.setItems(items);
                irs.add(ir);
            }
        }catch (Exception ex) {
            //xr.setMessage("Import failed, please check the data in "+rowIndex+" rows "+columnIndex+" columns ");
            message =  "Import failed, please check the data in "+rowIndex+" rows ";
        }
        return message;
    }


    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        if(cell == null) return "";
        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
            return cell.getStringCellValue();
        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
            return cell.getCellFormula() ;
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public static List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++)
        {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    private static int getRowNum(List<CellRangeAddress> listCombineCell, Cell cell, Sheet sheet){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }
    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet)
            throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    private static class InspectionReport{
        private String reportName;
        private Integer shift;
        private String line;
        private String stationCode;
        private Integer area;
        private Integer reportStatus;
        private List<InspectionItem> items;

        public String getReportName() {
            return reportName;
        }

        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public Integer getShift() {
            return shift;
        }

        public void setShift(Integer shift) {
            this.shift = shift;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getStationCode() {
            return stationCode;
        }

        public void setStationCode(String stationCode) {
            this.stationCode = stationCode;
        }

        public Integer getArea() {
            return area;
        }

        public void setArea(Integer area) {
            this.area = area;
        }

        public Integer getReportStatus() {
            return reportStatus;
        }

        public void setReportStatus(Integer reportStatus) {
            this.reportStatus = reportStatus;
        }

        public List<InspectionItem> getItems() {
            return items;
        }

        public void setItems(List<InspectionItem> items) {
            this.items = items;
        }
    }

    private static class InspectionItem{
        private String item;
        private String method;
        private String mode;
        private String standardValue;
        private String deviationValue;
        private Integer frequency;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getStandardValue() {
            return standardValue;
        }

        public void setStandardValue(String standardValue) {
            this.standardValue = standardValue;
        }

        public String getDeviationValue() {
            return deviationValue;
        }

        public void setDeviationValue(String deviationValue) {
            this.deviationValue = deviationValue;
        }

        public Integer getFrequency() {
            return frequency;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }
    }
}
