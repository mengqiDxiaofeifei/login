package com.mengqid.site.home.category;

import com.mengqid.entity.category.Category;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/view")
    public String view(){
        return "lyear_home_category";
    }

    /**
     * 列表
     * @param pageRequestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/categoryList")
    public PageResult categoryList(PageRequestVo pageRequestVo){
        PageResult pageReuslt = categoryService.categoryList(pageRequestVo);
        return pageReuslt;
    }

    /**
     * 新增、编辑
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveCategory")
    public Response saveCategory(Category category){
        return categoryService.saveCategory(category);
    }



    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/findCategoryById")
    public Response findCategoryById(Integer id){
        return categoryService.findCategoryById(id);
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleteCategoryByids")
    public Response deleteCategoryByids(@RequestParam("ids[]") List<Integer> ids){
        return categoryService.deleteCategoryByids(ids);
    }
}
