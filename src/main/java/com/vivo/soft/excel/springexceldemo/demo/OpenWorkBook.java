package com.vivo.soft.excel.springexceldemo.demo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-18.
 * @Time 21:48
 * @Description 打开现有工作簿
 * @Version 2.0.0
 */
public class OpenWorkBook {
    public static void main(String args[]) throws Exception {
        File file = new File(FilePathUtil.DIR + "白名单联想词关键字模板.xlsx");
        FileInputStream fIP = new FileInputStream(file);
        //Get the workbook instance for XLSX file
        XSSFWorkbook workbook = new XSSFWorkbook(fIP);
        if (file.isFile() && file.exists()) {
            System.out.println("openworkbook.xlsx file open successfully.");
        } else {
            System.out.println("Error to open openworkbook.xlsx file.");
        }
    }
}
