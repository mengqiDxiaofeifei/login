package com.mengqid.site.account;


import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.login.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/accountManage")
    public ModelAndView accountManage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lyear_pages_doc");
        return mv;
    }

    /**
     * 用户列表
     * @param pageRequestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/accountList")
    public PageResult accountList(PageRequestVo pageRequestVo,User user){
        PageResult pageReuslt = accountService.accountList(pageRequestVo,user);
        return pageReuslt;
    }

    /**
     * 新增、编辑
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveAccount")
    public Response saveAccount(User user){
       return accountService.saveAccount(user);
    }

    /**
     * 禁用启用
     */
    @ResponseBody
    @RequestMapping("/updateStatusByids")
    public Response updateStatusByids(@RequestParam("ids[]") List<Integer> ids,@RequestParam("status") Integer status){
        return accountService.updateStatusByids(ids,status);
    }

    /**
     * 根据id查询用户
     */
    @ResponseBody
    @RequestMapping("/findUserById")
    public Response findUserById(Integer id){
        return accountService.findUserById(id);
    }


    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping("/deleteUserByids")
    public Response deleteUserByids(@RequestParam("ids[]") List<Integer> ids){
        return accountService.deleteUserByids(ids);
    }

}
