package com.mengqid.site.qiantai.home.index;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.category.Category;
import com.mengqid.entity.climb.DouyiVideo;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.login.User;
import com.mengqid.entity.tag.Tag;
import com.mengqid.entity.video.Video;
import com.mengqid.mappers.CategoryMapper;
import com.mengqid.mappers.DouyiVideoMapper;
import com.mengqid.mappers.TagMapper;
import com.mengqid.mappers.VideoMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.ClimbDataUtil;
import com.mengqid.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private DouyiVideoMapper douyiVideoMapper;
    @RequestMapping("/index")
    public String home() {
        return "home_static/index";
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

    /**
     * 查询标签
     */
    @ResponseBody
    @RequestMapping("/qiantai/findTagList")
    public Response findTagList() {
        List<Tag> tags = tagMapper.selectAll();
        return new Response(200, tags, "成功");
    }


    /**
     * 查询最新video
     */
    @ResponseBody
    @RequestMapping("/qiantai/findVideoListNews")
    public Response findVideoListNews() {
        List<Video> videos = videoMapper.findVideoListNews();
        return new Response(200, videos, "成功");
    }

    /**
     * 查询最新video
     */
    @ResponseBody
    @RequestMapping("/qiantai/findVideoListdouyi")
    public Response findVideoListdouyi(@RequestParam("page") Integer currentPage) {
        Page<?> page = PageHelper.startPage(currentPage,6);
        List<DouyiVideo> videoUrl = douyiVideoMapper.findVideoUrl();
        return new Response(200, videoUrl, "成功");
    }


    /**
     * 查询热门video
     */
    @ResponseBody
    @RequestMapping("/qiantai/findVideoListHot")
    public Response findVideoListHot() {
        List<Video> videos = videoMapper.findVideoListHot();
        return new Response(200, videos, "成功");
    }


    /**
     * 查询热门video
     */
    @ResponseBody
    @RequestMapping("/qiantai/findDouyiVideo")
    public Response findDouyiVideo() {
        List<DouyiVideo> list = douyiVideoMapper.selectAll();
        return new Response(200, list, "成功");
    }
}
