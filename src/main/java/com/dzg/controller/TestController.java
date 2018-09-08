package com.dzg.controller;

import com.dzg.common.ApplicationContextHelper;
import com.dzg.common.JsonData;
import com.dzg.dao.SysAclModuleMapper;
import com.dzg.domain.SysAclModule;
import com.dzg.exception.ParamException;
import com.dzg.exception.PermissionException;
import com.dzg.param.TestVo;
import com.dzg.util.BeanValidator;
import com.dzg.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test exception");
        //  return JsonData.success("hello,permission");
        // return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
//        try {
//            Map<String, String> map = BeanValidator.validateObject(vo);
//            if(MapUtils.isNotEmpty(map)){
//                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    log.info("{}->{}",entry.getKey(),entry.getValue());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        BeanValidator.check(vo);
        return JsonData.success("test validate");
        //  return JsonData.success("hello,permission");
        // return JsonData.success("hello, permission");
    }
}
