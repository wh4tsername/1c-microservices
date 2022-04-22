import ru.mipt1c.homework.pominov.task1.Storage;
import ru.mipt1c.homework.task1.KeyValueStorage;
import ru.mipt1c.homework.task1.MalformedDataException;
import ru.mipt1c.homework.tests.task1.AbstractSingleFileStorageTest;
import ru.mipt1c.homework.tests.task1.Student;
import ru.mipt1c.homework.tests.task1.StudentKey;

public class KeyValueStorageTests extends AbstractSingleFileStorageTest {
    @Override
    protected KeyValueStorage<String, String> buildStringsStorage(String path) throws MalformedDataException {
        return new Storage<>(path);
    }

    @Override
    protected KeyValueStorage<Integer, Double> buildNumbersStorage(String path) throws MalformedDataException {
        return new Storage<>(path);
    }

    @Override
    protected KeyValueStorage<StudentKey, Student> buildPojoStorage(String path) throws MalformedDataException {
        return new Storage<>(path);
    }
}