package com.mengqid.site.common;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mengqid.constant.SystemConstant;
import com.mengqid.core.base.SysParam;
import com.mengqid.mappers.AuthorMapper;
import com.mengqid.mappers.DouyiVideoMapper;
import com.mengqid.mappers.SysParamMapper;
import com.mengqid.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TaskJobTimer {

    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SysParamMapper sysParamMapper;

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



    //从凌晨0~ 5点开始  每分钟执行一次
    @Scheduled(cron = "0 0/5 0-5 * * ?")
    public void scheduled2(){
        log.info("现在时间是" + new Date());
        SysParam sysParam = sysParamMapper.selectByKey(SystemConstant.CACHE_CURSOR);
        if(!CheckUtil.isEmpty(sysParam)){
            try{
                String cursor = commonService.climbVideoUrl(sysParam.getValue());
                sysParamMapper.updateBykey(SystemConstant.CACHE_CURSOR,cursor);
            }catch (Exception e){
                log.error("获取视频出错 ------------------ Exception: {}",e);
            }
        }
    }
}
