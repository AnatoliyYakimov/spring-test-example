import org.example.model.Person;
import org.example.repository.SimpleRepository;
import org.example.service.SimpleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                SimpleService.class
        }
)
public class T4MockingContext {

    @Autowired
    private SimpleService simpleService;

    @MockBean
    private SimpleRepository repositoryMock;

    @Test
    public void testContextInitialized() {
        Assert.assertNotNull(simpleService);
    }

    @Test
    public void testPerson() {
        //Given
        Person expectedPerson = new Person("Курт Кобейн", 27);
        when(repositoryMock.getPersonFromDb()).thenReturn(expectedPerson);

        //When
        Person actualPerson = simpleService.getPerson();

        //Then
        Assert.assertNotNull(actualPerson);
        Assert.assertEquals(expectedPerson, actualPerson);

        Mockito.verify(repositoryMock, Mockito.times(1)).getPersonFromDb();
    }

    @Test
    public void testThrowException() {
        //Given
        RuntimeException expectedException = new RuntimeException("Simple exception");
        when(repositoryMock.getPersonFromDb()).thenThrow(expectedException);

        //When
        Assert.assertThrows(RuntimeException.class, () -> simpleService.getPerson());

        //Than
        Mockito.verify(repositoryMock, Mockito.times(1)).getPersonFromDb();
    }

    @Test
    public void testCapture() {
        //Given
        Person expectedPerson = new Person("Курт Кобейн", 27);
        //Синтаксис для мока методов, возвращающих void
        Mockito.doNothing().when(repositoryMock).savePersonToDb(Mockito.any(Person.class));

        //When
        simpleService.savePerson(expectedPerson);

        //Than
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(repositoryMock, Mockito.times(0)).getPersonFromDb();
        Mockito.verify(repositoryMock).savePersonToDb(captor.capture());

        Person savedPerson = captor.getValue();
        Assert.assertEquals(expectedPerson, savedPerson);
    }

}
