package common.domain.validators;

import common.domain.Race;

public class RaceValidator implements IValidator<Race> {
    @Override
    public void validate(Race entity) throws ValidatorException {
        if (entity.getId() == null) {
            throw new ValidatorException("ID not set!");
        }

        if (entity.getDate() == null) {
            throw new ValidatorException("Date not set!");
        }

        if (entity.getLocation() == null) {
            throw new ValidatorException("Location not set!");
        }
    }
}
