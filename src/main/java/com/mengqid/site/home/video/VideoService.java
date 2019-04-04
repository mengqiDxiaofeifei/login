package com.mengqid.site.home.video;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.video.Video;
import com.mengqid.mappers.VideoMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.CommonUtil;
import com.mengqid.utils.ShortUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoService {


    @Autowired
    private VideoMapper videoMapper;

    public PageResult videoList(PageRequestVo pageRequestVo) {
        Page<?> page = PageHelper.startPage(pageRequestVo.getPage(), pageRequestVo.getLimit());
        List<Video> videos = videoMapper.selectAll();
        return CommonUtil.backPageResult(videos, page);
    }

    public Response saveVideo(Video video) {
        if (!CheckUtil.isEmpty(video)) {
            Video existVideo = videoMapper.selectByPrimaryKey(video);
            if (!CheckUtil.isEmpty(video.getId()) && !CheckUtil.isEmpty(existVideo)) {
                //修改
                video.setUpdateTime(new Date());
                videoMapper.updateByPrimaryKeySelective(video);
            } else {
                //新增
                video.setStatus(1);
                video.setUuid(ShortUUID.generate());
                video.setCreateTime(new Date());
                video.setUpdateTime(new Date());
                videoMapper.insert(video);
            }
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }


    public Response findVideoById(Integer id) {
        if (!CheckUtil.isEmpty(id)) {
            Video video = videoMapper.selectByPrimaryKey(id);
            return new Response(200, video, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response deleteVideoByids(List<Integer> ids) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> videoMapper.deleteByPrimaryKey(i));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response updateStatusByids(List<Integer> ids, Integer status) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> videoMapper.updateStatusById(i, status));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }
}
