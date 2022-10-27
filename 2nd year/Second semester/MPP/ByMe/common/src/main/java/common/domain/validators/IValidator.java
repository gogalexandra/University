package common.domain.validators;


public interface IValidator<T> {
    void validate(T entity) throws ValidatorException;
}
