package common.domain.validators;

import common.domain.Driver;

public class DriverValidator implements IValidator<Driver> {

    @Override
    public void validate(Driver entity) throws ValidatorException {
        if (entity.getId() == null) {
            throw new ValidatorException("ID not set!");
        }

        if (entity.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        }

        if (entity.getExperience() == null) {
            throw new ValidatorException("Experience cannot be null!");
        }

        if (entity.getCarId() == null) {
            throw new ValidatorException("CarId cannot be null!");
        }

        if (entity.getTeamId() == null) {
            throw new ValidatorException("CarId cannot be null!");
        }
    }
}
