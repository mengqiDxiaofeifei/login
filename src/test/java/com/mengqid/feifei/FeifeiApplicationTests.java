package com.mengqid.feifei;

import com.mengqid.FeifeiApplication;
import com.mengqid.site.common.CommonService;
import com.mengqid.utils.ClimbDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeifeiApplication.class)
public class FeifeiApplicationTests {

    @Resource
    private CommonService service;

    @Test
    public void contextLoads() {

        InputStream inputStream = ClimbDataUtil.httpDownloadInputStream("http://mvvideo10.meitudata.com/5c931fb0c65ca5o77rgodg2703.mp4");
        String realVideo = service.uploadVideo(inputStream, "rB8ni1y4ITqAONRrAD3joUCLYTE489'");
        System.out.println("realVideo = " + realVideo);
    }

}
