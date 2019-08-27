package com.mengqid.site.common;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mengqid.constant.SystemConstant;
import com.mengqid.core.base.SysParam;
import com.mengqid.entity.Author.Author;
import com.mengqid.entity.climb.DouyiVideo;
import com.mengqid.mappers.AuthorMapper;
import com.mengqid.mappers.DouyiVideoMapper;
import com.mengqid.mappers.SysParamMapper;
import com.mengqid.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class TaskJobTimer {

    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SysParamMapper sysParamMapper;
    @Autowired
    private DouyiVideoMapper douyiVideoMapper;
    @Autowired
    private AuthorMapper authorMapper;

//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void scheduled1(){
//        log.info("现在时间是" + new Date());
//       SysParam sysParam = sysParamMapper.selectByKey(SystemConstant.CACHE_CURSOR);
//       if(!CheckUtil.isEmpty(sysParam)){
//           try{
//               String cursor = commonService.climbVideoUrl(sysParam.getValue());
//               sysParamMapper.updateBykey(SystemConstant.CACHE_CURSOR,cursor);
//           }catch (Exception e){
//                log.error("获取视频出错 ------------------ Exception: {}",e);
//           }
//       }
//        log.info("定时器执行完毕 ----------------------" + new Date());
//    }




//    /**
//     * 定时获取抖音视频及详情
//     * (从凌晨0~ 5点开始  每分钟执行一次)
//     */
//    //@Scheduled(cron = "0 0/5 0-5 * * ?")
//    public void scheduled2(){
//        log.info("现在时间是" + new Date());
//        SysParam sysParam = sysParamMapper.selectByKey(SystemConstant.CACHE_CURSOR);
//        if(!CheckUtil.isEmpty(sysParam)){
//            try{
//                String cursor = commonService.climbVideoUrl(sysParam.getValue());
//                sysParamMapper.updateBykey(SystemConstant.CACHE_CURSOR,cursor);
//            }catch (Exception e){
//                log.error("获取视频出错 ------------------ Exception: {}",e);
//            }
//        }
//    }

    /**
     * 初期服务器配置简陋，故如此
     * 定时删除抖音视频及详情
     * (每天上午6点执行一次)
     */
   // @Scheduled(cron = "0 0 6 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void scheduled3(){
        log.info("删除视频定时器开始执行-------------现在时间是" + new Date());
        List<DouyiVideo> list = douyiVideoMapper.findYesterDay();
        if(!CheckUtil.isEmpty(list) && list.size() > 0){
            list.forEach(l -> {
                try{
                    String videoUrl = l.getVideoUrl();
                    storageClient.deleteFile(videoUrl.substring(SystemConstant.baseUrl.length(),videoUrl.length()));
                    Example example = new Example(DouyiVideo.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("uuid",l.getUuid());
                    douyiVideoMapper.deleteByExample(example);
                    Example example2 = new Example(Author.class);
                    Example.Criteria criteria2 = example2.createCriteria();
                    criteria2.andEqualTo("uid",l.getAuthorUid());
                    authorMapper.deleteByExample(example2);
                }catch (Exception e){
                    log.error("删除视频出错 ------------------ Exception: {}",e);
                }
            });
        }
        log.info("删除视频定时器执行完毕  --------------" + new Date());
    }

    public static void main(String[] args) {
    String s = "http://3.14.4.147/group1/M00/00/02/rB8ni1y4ITqAONRrAD3joUCLYTE489.mp4";
   s =  s.substring(SystemConstant.baseUrl.length(),s.length());
        System.out.println("s = " + s);

    }
}
