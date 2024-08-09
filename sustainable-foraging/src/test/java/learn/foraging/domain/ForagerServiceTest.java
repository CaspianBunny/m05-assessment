package learn.foraging.domain;

import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static learn.foraging.TestHelper.makeResult;

public class ForagerServiceTest {

    ForagerService service = new ForagerService(new ForagerRepositoryDouble());


    @Test
    void shouldAdd() {
        Forager arg = new Forager(0, "John", "Doe", "KY");
        Result <Forager> expected = makeResult(null, new Forager(5, "John", "Doe", "KY"));
        Result <Forager> actual = service.add(arg);

    }
}
