package com.mengqid.site.home.tag;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.tag.Tag;
import com.mengqid.mappers.TagMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public PageResult tagList(PageRequestVo pageRequestVo) {
        Page<?> page = PageHelper.startPage(pageRequestVo.getPage(), pageRequestVo.getLimit());
        List<Tag> tags = tagMapper.selectAll();
        return CommonUtil.backPageResult(tags, page);
    }

    public Response saveTag(Tag tag) {
        if (!CheckUtil.isEmpty(tag)) {
            Tag existTag = tagMapper.selectByPrimaryKey(tag);
            if (!CheckUtil.isEmpty(tag.getId()) && !CheckUtil.isEmpty(existTag)) {
                //修改
                tag.setUpdateTime(new Date());
                tagMapper.updateByPrimaryKeySelective(tag);
            } else {
                //新增
                tag.setCreateTime(new Date());
                tag.setUpdateTime(new Date());
                tagMapper.insert(tag);
            }
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response findTagById(Integer id) {
        if (!CheckUtil.isEmpty(id)) {
            Tag tag = tagMapper.selectByPrimaryKey(id);
            return new Response(200, tag, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response deleteTagByids(List<Integer> ids) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> tagMapper.deleteByPrimaryKey(i));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }
}
