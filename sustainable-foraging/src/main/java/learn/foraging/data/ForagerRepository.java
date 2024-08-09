package learn.foraging.data;

import learn.foraging.models.Forager;

import java.util.List;

public interface  ForagerRepository {
    List<Forager> findAll();

    Forager findById(int id);

    List<Forager> findByLastName(String lastNamePrefix);

    List<Forager> findByState(String stateAbbr);

    Forager add(Forager forager);

    boolean update(Forager forager);

    Forager findByFirstAndLastName(String firstName, String lastName);


}
