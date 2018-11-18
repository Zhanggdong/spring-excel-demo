package com.vivo.soft.excel.springexceldemo.controller;

import com.vivo.soft.excel.springexceldemo.entity.AssociationKey;
import com.vivo.soft.excel.springexceldemo.service.AssociationKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 14:31
 * @Description TODO
 * @Version 2.0.0
 */
@Controller
@RequestMapping("associationKeys")
public class AssociationKeyController {

    @Autowired
    private AssociationKeyService associationKeyService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/tpl/associationKey/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseData<Page<AssociationKey>> list(@RequestParam Integer page,@RequestParam Integer rows){
        ResponseData<Page<AssociationKey>> resp = new ResponseData<Page<AssociationKey>>();
        try{
            Page<AssociationKey> data = associationKeyService.findAssociationKeyNoCriteria(page-1,rows);
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
