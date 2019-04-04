package com.mengqid.site.home.tag;

import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/view")
    public String view() {
        return "lyear_home_tag";
    }

    /**
     * 列表
     *
     * @param pageRequestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/tagList")
    public PageResult tagList(PageRequestVo pageRequestVo) {
        PageResult pageReuslt = tagService.tagList(pageRequestVo);
        return pageReuslt;
    }

    /**
     * 新增、编辑
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveTag")
    public Response saveTag(Tag tag) {
        return tagService.saveTag(tag);
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/findTagById")
    public Response findTagById(Integer id) {
        return tagService.findTagById(id);
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleteTagByids")
    public Response deleteTagByids(@RequestParam("ids[]") List<Integer> ids) {
        return tagService.deleteTagByids(ids);
    }


}
