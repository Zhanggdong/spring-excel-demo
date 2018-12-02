package com.vivo.soft.excel.springexceldemo.controller;

import com.vivo.soft.excel.springexceldemo.dto.AssociationGameDto;
import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyGameService;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyService;
import com.vivo.soft.excel.springexceldemo.util.excelTools.ExcelUtils;
import com.vivo.soft.excel.springexceldemo.util.excelTools.JsGridReportBase;
import com.vivo.soft.excel.springexceldemo.util.excelTools.TableData;
import com.vivo.soft.excel.springexceldemo.util.excelTools.TableHeaderMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 风骚的GRE
 * @Descriptions TODO
 * @date 2018/12/2.
 */
@Controller
@RequestMapping("/export")
@SuppressWarnings("unchecked")
public class ExcelExportController {

    @Autowired
    private AssociationKeyService associationKeyService;
    @Autowired
    private AssociationKeyGameService associationGameService;

    /**
     * 行合并Excel导出，获取的数据格式是List<Object[]>
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=rowSpanExport")
    public void exportRowSpan(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        List<AssociationGameDto> list = associationGameService.findDtoAll();
        String title = "行合并Excel表";
        String[] parents = new String[]{"干预关键字","干预游戏"};
        String[][] children = new String[][]{{"干预关键字", "生效时间", "失效时间"},{"游戏ID", "游戏名称", "干预位置"}};
        String[] hearders = new String[] {"干预关键字", "生效时间", "失效时间", "干预关键字ID", "游戏ID", "游戏名称", "干预位置"};//表头数组
        String[] fields = new String[] {"keyName", "fromDate", "endDate", "keyId", "gameId", "gameName", "priority"};//People对象属性数组
        int spanCount = 3;//需要合并的列数。从第1列开始到指定列。
        TableHeaderMetaData tableHeader = ExcelUtils.createTableHeader(parents,children);
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders,spanCount),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, "admin", td);
    }

    private List<AssociationKey> loadRowSpanData() {
        List<AssociationKey> associationKeys = associationKeyService.findAll();
        return associationKeys;
    }
}
