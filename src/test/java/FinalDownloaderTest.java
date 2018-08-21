import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

public class FinalDownloaderTest {

    private String URL = "http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_";

    @Ignore
    public void testFinalDownloaderTest(){
        Request request = new Request(URL);
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        FinalDownloader finalDownloader = new FinalDownloader();
        Page page = finalDownloader.download(request,task);
        System.out.println(page);
    }
    @Test
    public void test_getRedirectInfo(){
        FinalDownloader finalDownloader = new FinalDownloader();
        finalDownloader.getRedirectInfo(URL);
    }
    @Ignore
    public void testFinalDownloaderTestBaiduZhidao(){
        Request request = new Request("http://baike.baidu.com/subview/38681/5279942.htm");
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        FinalDownloader finalDownloader = new FinalDownloader();
        Page page = finalDownloader.download(request,task);
        System.out.println(page);
    }
}
