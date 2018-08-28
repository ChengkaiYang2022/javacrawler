package downloader;

import downloader.RedirectDownloader;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

import java.util.ArrayList;
import java.util.List;

public class RedirectDownloaderTest {

    private String URL = null;
    @Before
    public void init(){
        URL = "http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_";


    }

    // 测试百度跳转链接
    @Test
    public void testFinalDownloaderTest(){
        Request request = new Request(URL);
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        RedirectDownloader redirectDownloader = new RedirectDownloader();
        Page page = redirectDownloader.download(request,task);

        assertEquals(page.getResultItems().get("redirectUrl"),"https://www.cnblogs.com/showing/p/6752611.html");
        System.out.println(page.getResultItems().get("redirectUrl")+" 测试通过");

    }

    // 测试百度知道连接
    @Test
    public void testRedirectDownloaderTestBaiduZhidao(){
        Request request = new Request("http://baike.baidu.com/subview/38681/5279942.htm");
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        RedirectDownloader redirectDownloader = new RedirectDownloader();
        Page page = redirectDownloader.download(request,task);
        assertEquals(page.getResultItems().get("redirectUrl"),"https://baike.baidu.com/item/%E9%82%93%E8%B6%85/5681");
        System.out.println(page.getResultItems().get("redirectUrl")+" 测试通过");
    }


}
