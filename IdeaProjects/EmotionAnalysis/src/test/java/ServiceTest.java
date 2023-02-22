import com.emotion.analysis.bean.Content;
import com.emotion.analysis.service.ContentService;
import com.emotion.analysis.service.impl.ContentServiceImpl;
import org.junit.Test;

import javax.xml.ws.Service;
import java.util.List;

public class ServiceTest {
    @Test
    public void getAllTest() {
        ContentService contentService = new ContentServiceImpl();
        List<Content> contents = contentService.getAllContent();
        contents.forEach(System.out::println);
    }
}
