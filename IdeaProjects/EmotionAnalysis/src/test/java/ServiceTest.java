import com.emotion.analysis.bean.Content;
import com.emotion.analysis.service.ContentService;
import com.emotion.analysis.service.impl.ContentServiceImpl;
import org.junit.Test;

import java.util.List;

public class ServiceTest {
    @Test
    public void getAllTest() {
        ContentService contentService = new ContentServiceImpl();
        List<Content> contents = contentService.getAllContents();
        contents.forEach(System.out::println);
    }
}
