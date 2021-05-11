import org.example.model.Person;
import org.example.repository.SimpleRepositoryImpl;
import org.example.service.SimpleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(
    classes = {
            SimpleService.class,
            SimpleRepositoryImpl.class
    }
)
public class T2Context {

    @Autowired
    private SimpleService simpleService;

    @Test
    public void testContextInitialized() {
        Assert.assertNotNull(simpleService);
    }

    @Test
    public void testPersonNonNull() {
        Person person = simpleService.getPerson();
        Assert.assertNotNull(person);
    }
}
