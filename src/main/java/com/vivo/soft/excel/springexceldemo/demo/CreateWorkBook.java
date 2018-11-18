package com.vivo.soft.excel.springexceldemo.demo;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-18.
 * @Time 21:44
 * @Description 创建空白工作簿
 * @Version 2.0.0
 */
public class CreateWorkBook {
    public static void main(String[] args) throws Exception {
        //Create Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create file system using specific name
        String src_xlspath = "D:\\work_idea\\springboot\\spring-excel-demo\\src\\main\\resources\\doc\\";
        FileOutputStream out = new FileOutputStream(
                new File(src_xlspath+"createworkbook.xlsx"));
        //write operation workbook using file out object
        workbook.write(out);
        out.close();
        System.out.println("createworkbook.xlsx written successfully");
    }
}
