package com.mengqid.site.qiantai.home.index;

import com.mengqid.entity.category.Category;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.login.User;
import com.mengqid.mappers.CategoryMapper;
import com.mengqid.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping("/index")
    public String home() {
        return "/home_static/index";
    }


    /**
     * 查询分类
     */
    @ResponseBody
    @RequestMapping("/qiantai/findCategoryByType")
    public Response findCategoryByType(String type) {
        if (!CheckUtil.isEmpty(type)) {
            List<Category> categories = categoryMapper.findCategoryByType(type);
            return new Response(200, categories, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

}
