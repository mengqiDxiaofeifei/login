package com.mengqid.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengqid.entity.api.pojo.Videos;
import com.mengqid.entity.api.vo.VideosVo;
import com.mengqid.mappers.VideosMapperCustom;
import com.mengqid.utils.wechat.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private VideosMapperCustom videosMapperCustom;

    @Transactional(propagation = Propagation.REQUIRED)
    public PagedResult getAllVideos(Integer isSaveRecord,
                                    Integer page, Integer pageSize) {

        // 保存热搜词
        String desc = "";
        String userId = "";
//        if (isSaveRecord != null && isSaveRecord == 1) {
//            SearchRecords record = new SearchRecords();
//            String recordId = sid.nextShort();
//            record.setId(recordId);
//            record.setContent(desc);
//            searchRecordsMapper.insert(record);
//        }

        PageHelper.startPage(page, pageSize);
        List<VideosVo> list = videosMapperCustom.queryAllVideos(desc, userId);

        PageInfo<VideosVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

}
