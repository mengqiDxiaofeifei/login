package com.mengqid.site.home.banner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.banner.Banner;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.mappers.BannerMapper;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;


    public PageResult bannerList(PageRequestVo pageRequestVo) {
            Page<?> page = PageHelper.startPage(pageRequestVo.getPage(), pageRequestVo.getLimit());
            List<Banner> banners = bannerMapper.selectAll();
            return CommonUtil.backPageResult(banners, page);
        }

    public Response saveBanner(Banner banner) {
        if (!CheckUtil.isEmpty(banner)) {
            Banner exsitBanner = bannerMapper.selectByPrimaryKey(banner);
            if (!CheckUtil.isEmpty(banner.getId()) && !CheckUtil.isEmpty(exsitBanner)) {
                //修改
                banner.setUpdateTime(new Date());
                bannerMapper.updateByPrimaryKeySelective(banner);
            } else {
                //新增
                banner.setStatus(1);
                banner.setCreateTime(new Date());
                banner.setUpdateTime(new Date());
                bannerMapper.insert(banner);
            }
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response updateStatusByids(List<Integer> ids, Integer status) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> bannerMapper.updateStatusById(i, status));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response findBannerById(Integer id) {
        if (!CheckUtil.isEmpty(id)) {
            Banner banner = bannerMapper.selectByPrimaryKey(id);
            return new Response(200, banner, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response deleteBannerByids(List<Integer> ids) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> bannerMapper.deleteByPrimaryKey(i));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }
}
