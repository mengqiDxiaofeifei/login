package com.mengqid.site.account;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengqid.entity.common.PageRequestVo;
import com.mengqid.entity.common.PageResult;
import com.mengqid.entity.common.Response;
import com.mengqid.entity.login.User;
import com.mengqid.mappers.UserMapper;
import com.mengqid.site.login.LoginService;
import com.mengqid.utils.CheckUtil;
import com.mengqid.utils.CommonUtil;
import com.mengqid.utils.PasswordEncoderUtil;
import com.mengqid.utils.ShortUUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginService loginService;

    public PageResult accountList(PageRequestVo pageRequestVo, User user) {
        Page<?> page = PageHelper.startPage(pageRequestVo.getPage(), pageRequestVo.getLimit());
        List<User> users = userMapper.accountPage(user);
        return CommonUtil.backPageResult(users, page);
    }

    public Response saveAccount(User user) {
        if (!CheckUtil.isEmpty(user)) {
            User exsitUser = userMapper.selectByPrimaryKey(user);
            if (!CheckUtil.isEmpty(user.getId()) && !CheckUtil.isEmpty(exsitUser)) {
                //修改
                user.setUpdateTime(new Date());
                user.setPassword(null);
                userMapper.updateByPrimaryKeySelective(user);
            } else {
                //新增
                user.setPassword(PasswordEncoderUtil.passwordEncoder(user.getPassword()));
                user.setUserUuid(ShortUUID.generate());
                user.setStatus(1);
                user.setType(0);
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                userMapper.insert(user);
            }
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response updateStatusByids(List<Integer> ids, Integer status) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> userMapper.updateStatusById(i, status));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response findUserById(Integer id) {
        if (!CheckUtil.isEmpty(id)) {
            User user = userMapper.selectByPrimaryKey(id);
            user.setPassword("123456");
            return new Response(200, user, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public Response deleteUserByids(List<Integer> ids) {
        if (!CheckUtil.isEmpty(ids) && ids.size() != 0) {
            ids.forEach(i -> userMapper.deleteByPrimaryKey(i));
            return new Response(200, null, "成功");
        } else {
            return new Response(400, null, "请求参数不能为空！");
        }
    }

    public com.mengqid.entity.Response findUserByMobile(String mobile) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("telephone", mobile);
        User user = userMapper.selectOneByExample(example);
        return com.mengqid.entity.Response.buildSuccessResponse(user);
    }

    public com.mengqid.entity.Response findUserByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        return com.mengqid.entity.Response.buildSuccessResponse(user);
    }

    public Boolean checkSmsCode(String phone, String smsCode) {
        if (CheckUtil.isEmpty(phone) && CheckUtil.isEmpty(smsCode)) {
            return false;
        } else {
            return loginService.checkCode(phone, smsCode);
        }
    }
}
