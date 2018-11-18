package com.vivo.soft.excel.springexceldemo.controller;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKeyGame;
import com.vivo.soft.excel.springexceldemo.query.AssociationKeyGameQuery;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 21:47
 * @Description TODO
 * @Version 2.0.0
 */
@Controller
@RequestMapping("associationKeys/games")
public class AssociationKeyGameController {

    @Autowired
    private AssociationKeyGameService associationKeyGameService;

    public String index(){
        return "/tpl/associationKey/gameIndex";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseData<Page<AssociationKeyGame>> list(@RequestParam Integer page,
                                                       @RequestParam Integer rows,
                                                       @ModelAttribute AssociationKeyGameQuery query){
        ResponseData<Page<AssociationKeyGame>> resp = new ResponseData<Page<AssociationKeyGame>>();
        try{
            Page<AssociationKeyGame> data = associationKeyGameService.findAssociationKeyGameCriteria(page-1,rows,query);
            resp.setCode(0);
            resp.setMessage("查询成功！");
            resp.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage("查询失败！");
        }
        return resp;
    }
}
