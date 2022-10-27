package common.domain.validators;


import common.domain.Car;

public class CarValidator implements IValidator<Car> {

    @Override
    public void validate(Car entity) throws ValidatorException{
        if (entity.getId() == null) {
            throw new ValidatorException("Name cannot be null!");
        }
        if (entity.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        }
        if (entity.getMaxSpeed() == null) {
            throw new ValidatorException("MaxSpeed cannot be null!");
        }
        if (entity.getHorsepower() == null) {
            throw new ValidatorException("HorsePower cannot be null!");
        }
    }
}
