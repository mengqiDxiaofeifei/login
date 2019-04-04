package com.mengqid.site.home.video;

import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/view")
    public String view() {
        return "lyear_home_video";
    }

    /**
     * 列表
     *
     * @param pageRequestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/videoList")
    public PageResult videoList(PageRequestVo pageRequestVo) {
        PageResult pageReuslt = videoService.videoList(pageRequestVo);
        return pageReuslt;
    }

    /**
     * 新增、编辑
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveVideo")
    public Response saveVideo(Video video) {
        return videoService.saveVideo(video);
    }

    /**
     * 禁用启用
     */
    @ResponseBody
    @RequestMapping("/updateStatusByids")
    public Response updateStatusByids(@RequestParam("ids[]") List<Integer> ids, @RequestParam("status") Integer status){
        return videoService.updateStatusByids(ids,status);
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/findVideoById")
    public Response findVideoById(Integer id) {
        return videoService.findVideoById(id);
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleteVideoByids")
    public Response deleteVideoByids(@RequestParam("ids[]") List<Integer> ids) {
        return videoService.deleteVideoByids(ids);
    }

}
