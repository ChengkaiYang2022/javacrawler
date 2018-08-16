import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;

public class FinalDownloaderTest {
    @Test
    public void testFinalDownloaderTest(){
        Request request = new Request("http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_");
        Task task = Site.me().setDomain("localhost").setCycleRetryTimes(5).toTask();
        FinalDownloader finalDownloader = new FinalDownloader();
        Page page = finalDownloader.download(request,task);
        System.out.println(page);
    }
}
