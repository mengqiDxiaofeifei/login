package com.mengqid.site.account;


import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.login.User;
import io.swagger.models.auth.In;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/account")
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
    public PageResult accountList(PageRequestVo pageRequestVo){
        PageResult pageReuslt = accountService.accountList(pageRequestVo);
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
    public Response updateStatusByids(List<Integer> ids, Integer status){
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

}
