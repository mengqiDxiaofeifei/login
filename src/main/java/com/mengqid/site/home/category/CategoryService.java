package com.mengqid.site.home.category;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.category.Category;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.mappers.CategoryMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    public PageResult categoryList(PageRequestVo pageRequestVo) {
        Page<?> page = PageHelper.startPage(pageRequestVo.getPage(), pageRequestVo.getLimit());
        List<Category> categories = categoryMapper.selectAll();
        return CommonUtil.backPageResult(categories, page);
    }

    public Response saveCategory(Category category) {
        if (!CheckUtil.isEmpty(category)) {
            Category existCategory = categoryMapper.selectByPrimaryKey(category);
            if (!CheckUtil.isEmpty(category.getId()) && !CheckUtil.isEmpty(existCategory)) {
                //修改
                category.setUpdateTime(new Date());
                categoryMapper.updateByPrimaryKeySelective(category);
            } else {
                //新增
                category.setCreateTime(new Date());
                category.setUpdateTime(new Date());
                categoryMapper.insert(category);
            }
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response findCategoryById(Integer id) {
        if (!CheckUtil.isEmpty(id)) {
            Category category = categoryMapper.selectByPrimaryKey(id);
            return new Response(200, category, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response deleteCategoryByids(List<Integer> ids) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> categoryMapper.deleteByPrimaryKey(i));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }
}
