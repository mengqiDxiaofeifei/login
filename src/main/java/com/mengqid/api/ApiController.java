package com.mengqid.api;


import com.mengqid.entity.api.pojo.Videos;
import com.mengqid.utils.wechat.utils.KkJsonResult;
import com.mengqid.utils.wechat.utils.PagedResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微信小程序接口
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @Resource
    private ApiService apiService;

    /**
     *
     * @Description: 分页和搜索查询视频列表
     * isSaveRecord：1 - 需要保存
     * 				 0 - 不需要保存 ，或者为空的时候
     */
    @PostMapping(value="/showAll")
    public KkJsonResult showAll(Integer isSaveRecord,
                                Integer page, Integer pageSize) throws Exception {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PagedResult result = apiService.getAllVideos(isSaveRecord, page, pageSize);
        return KkJsonResult.ok(result);
    }
}
