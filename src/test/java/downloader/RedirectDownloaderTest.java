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

    private List<String> testUrl = new ArrayList<String>();
    private String URL = "http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_";

    @Before
    public void init(){
        String URL_CNBLOG = "http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_";
        String URL_TIANHONG = "http://www.baidu.com/link?url=---33rXjyw861WwuVBNKwS0yaha5l_z0I9tBaJ-_3JMIK98rHB6WWT3EW3g1IshPfr1yD8IGCj1l83arAFAmB2mK4ZVOkFrrMG9c4UzIOMUA8tE8gQwnhtXtFFV6Hfs0";
        String URL3 = "http://www.baidu.com/link?url=---yJOodqyT2CM0ueRB8pF3fWwYEe8Bb0GQp8rctUtho_Q7N5VnGO9bK8I_4D5rb021bASfA7sIvl6WqjisCaK";

        testUrl.add(URL_CNBLOG);
        testUrl.add(URL_TIANHONG);
        testUrl.add(URL3);

    }

    @Test
    public void testFinalDownloaderTest(){
        Request request = new Request(URL);
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        RedirectDownloader redirectDownloader = new RedirectDownloader();
        Page page = redirectDownloader.download(request,task);

        assertEquals(page.getResultItems().get("redirectUrl"),"https://www.cnblogs.com/showing/p/6752611.html");
        System.out.println(page.getResultItems().get("redirectUrl")+" 测试通过");

    }

    @Test
    public void test_getRedirectInfo(){
        RedirectDownloader redirectDownloader = new RedirectDownloader();
        for (String url:this.testUrl){
            String result = redirectDownloader.getRedirectUrl(url);
            assertNotNull(result);
            System.out.println(result);
        }

    }
    // 测试百度知道 连接
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
