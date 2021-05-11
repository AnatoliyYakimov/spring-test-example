import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
public class T1Basics {

    private static final String EXPECTED = "Text data from dimple resource";

    private static String RESOURCE_PATH;

    private InputStream resourceInputStream;

    @BeforeClass
    public static void beforeClass() {
        RESOURCE_PATH = "simple-resource.txt";
    }

    @Before
    public void before() throws IOException {
        resourceInputStream = new ClassPathResource(RESOURCE_PATH).getInputStream();
    }

    @Test
    public void test() throws IOException {
        byte[] data = resourceInputStream.readAllBytes();
        String content = new String(data);
        Assert.assertEquals("Simple resource content and expected should match", EXPECTED, content);
    }

    @After
    public void cleanup() throws IOException {
        if (resourceInputStream != null) {
            resourceInputStream.close();
        }
    }
}
