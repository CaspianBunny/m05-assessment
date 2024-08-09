package learn.foraging.domain;

import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;

import java.util.List;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findByLastName(prefix);
    }

    public Result<Forager> add(Forager forager) {
        Result<Forager> result = validate(forager);

        if (forager != null && forager.getId() > 0) {
            result.addErrorMessage("foragerId cannot be set for `add` operation.");
            return result;
        }

        if (result.isSuccess()) {
            forager = repository.add(forager);
            result.setPayload(forager);
        }

        return result;
    }

    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result();

        if (forager == null) {
            result.addErrorMessage("forager cannot be null.");
            return result;
        }

        if (forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("forager first name is required.");
        }

        if (forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("forager last name is required.");
        }

        if (forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("forager state is required.");
        }

        if (result.isSuccess()) {
            Forager existing = repository.findByFirstAndLastName(forager.getFirstName(), forager.getLastName());
            if (existing != null && existing.getId() != (forager.getId())) {
                result.addErrorMessage("forager first and last name already exists.");
            }

        }

        return result;
    }
}
