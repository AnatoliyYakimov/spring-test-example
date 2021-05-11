import org.example.model.Person;
import org.example.repository.SimpleRepository;
import org.example.service.SimpleService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class T3Mocking {

    private static SimpleService simpleService;

    private static SimpleRepository repositoryMock;

    private static final Person EXPECTED_PERSON = new Person("Курт Кобейн", 27);

    @BeforeClass
    public static void initService() {
        repositoryMock = Mockito.mock(SimpleRepository.class);
        Mockito.when(repositoryMock.getPersonFromDb()).thenReturn(EXPECTED_PERSON);
        simpleService = new SimpleService(repositoryMock);
    }

    @Test
    public void testContextInitialized() {
        Assert.assertNotNull(simpleService);
    }

    @Test
    public void testPersonNonNull() {
        Person actualPerson = simpleService.getPerson();

        Assert.assertNotNull(actualPerson);
        Assert.assertEquals(EXPECTED_PERSON, actualPerson);

        Mockito.verify(repositoryMock).getPersonFromDb();
    }

}
