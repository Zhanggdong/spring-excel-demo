package com.vivo.soft.excel.springexceldemo.demo;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-18.
 * @Time 20:32
 * @Description TODO
 * @Version 2.0.0
 */
public class EXCELPioParse {
    public static void main(String[] args) throws FileNotFoundException {
        getMyXLS();
    }

    public static void getMyXLS() throws FileNotFoundException {
          /*文件的方式读取*/
        // File xlsOrxlsxFile = new File("D:/EXCEL/55.xls");
         /*流处理的方式*/
        // FileInputStream  xlsOrxlsxFile = new FileInputStream("D:/EXCEL/55.xls");
        //  FileInputStream  xlsOrxlsxFile = new FileInputStream("E:/企业信息表.xls");
        FileInputStream  xlsOrxlsxFile = new FileInputStream("D:/EXCEL/88.xlsx");
         /*
        if(!xlsOrxlsxFile.exists())
        {
            System.out.println(00);
            return ;
        }
        */
        /*文件不存在的情况直接终止返回*/
        if(xlsOrxlsxFile==null)
        {
            System.out.println("文件不存在...");
            return;
        };
        try {
            /*通过流或者是文件创建一个Workbook对象*/
            Workbook wb = WorkbookFactory.create(xlsOrxlsxFile);
            /*获取EXCEL表的子表sheet的总数*/
            int sheetNum = wb.getNumberOfSheets();
            /*创建一个子表对象Sheet*/
            Sheet sheet = null;
            /*遍历子表对象从下标为0的子表对象开始进行遍历*/
            for(int sheetIndex = 0;sheetIndex<sheetNum;sheetIndex++)
            {
                /*输出当前子表下标*/
                System.out.println("sheet:"+sheetIndex);
                /*通过下标获取EXCEL表格当前子表对象*/
                sheet = wb.getSheetAt(sheetIndex);
                /*创建一个行对象*/
                Row row = null;
                /*获取第一行下标*/
                int firstRowNum = sheet.getFirstRowNum();
                /*获取最后一行下标*/
                int lastRowNum = sheet.getLastRowNum();
                /*遍历每行遍历row(行 0开始)*/
                for (int rowIndex = firstRowNum;rowIndex<=lastRowNum;rowIndex++ )
                {
                    /*获取当前行对象*/
                    row = sheet.getRow(rowIndex);
                    /*判断如果行存在*/
                    if(null != row)
                    {
                        /*通过行取到第一格下标*/
                        int firstCellNum = row.getFirstCellNum();
                        /*通过行对象取得最后一格下标*/
                        int lastCellNum = row.getLastCellNum();
                        /*遍历行对象的每格，遍历cell（列 0开始）*/
                        for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++)
                        {
                            /*通过格子index和行返回设置返回格子对象*/
                            Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);
                            /*如果存在cell对象*/
                            if (cell!=null ) {
                                /*创建一个对象保存cell数据*/
                                Object cellValue = null;//cellValue的值
                                /*分支循环(条件为cell类型)*/
                                switch (cell.getCellType())
                                {
                                    /*字符串类型*/
                                    case Cell.CELL_TYPE_STRING:
                                        System.out.println(cell.getRichStringCellValue().getString());
                                        cellValue = cell.getRichStringCellValue() .getString();
                                        break;
                                     /*数字类型*/
                                    case Cell.CELL_TYPE_NUMERIC:
                                        /*数字类型中时间类型的判断*/
                                        if (DateUtil.isCellDateFormatted(cell))
                                        {
                                            System.out.println(cell.getDateCellValue());
                                            cellValue= cell.getDateCellValue();
                                            //TODO 可以按日期格式转换
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            String time = formatter.format(cellValue);
                                            System.out.println("时间数据:"+time);
                                        } else {
                                            System.out.println("数字数据:"+cell.getNumericCellValue());
                                            cellValue=cell.getNumericCellValue();
                                        }
                                        break;
                                    /*布尔类型*/
                                    case Cell.CELL_TYPE_BOOLEAN:
                                        System.out.println("布尔数据"+cell.getBooleanCellValue());
                                        cellValue = cell.getBooleanCellValue();
                                        break;
                                    /*EXCEL表格内公式类型*/
                                    case Cell.CELL_TYPE_FORMULA:
                                        System.out.println(cell.getCellFormula());
                                        cellValue = cell.getCellFormula();
                                        break;
                                    /*如果没有以上类型匹配，则默认处理的方式*/
                                    default:
                                        System.out.println("没有匹配的数据类型..");
                                }
                                /*switch循环完毕后得到一行每格的数据*/
                                System.out.println("value:"+cellValue);
                            }
                            else
                            {
                                //TODO cell is null 用 *** 代替输出
                                System.out.println("***");
                            }
                        }//end cell
                    }
                    else
                    {
                        //TODO row is null
                        System.out.println("EXCEL表中数据为空");
                    }
                }//行遍历结束
            }//表遍历结束
        } catch (InvalidFormatException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
