package com.mengqid.site.home.banner;

import com.mengqid.entity.banner.Banner;
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
@RequestMapping("/manage/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/view")
    public String view(){
        return "lyear_home_banner";
    }

    /**
     * 用户列表
     * @param pageRequestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/bannerList")
    public PageResult bannerList(PageRequestVo pageRequestVo){
        PageResult pageReuslt = bannerService.bannerList(pageRequestVo);
        return pageReuslt;
    }

    /**
     * 新增、编辑
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveBanner")
    public Response saveBanner(Banner banner){
        return bannerService.saveBanner(banner);
    }

    /**
     * 禁用启用
     */
    @ResponseBody
    @RequestMapping("/updateStatusByids")
    public Response updateStatusByids(@RequestParam("ids[]") List<Integer> ids, @RequestParam("status") Integer status){
        return bannerService.updateStatusByids(ids,status);
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/findBannerById")
    public Response findBannerById(Integer id){
        return bannerService.findBannerById(id);
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleteBannerByids")
    public Response deleteBannerByids(@RequestParam("ids[]") List<Integer> ids){
        return bannerService.deleteBannerByids(ids);
    }

}
