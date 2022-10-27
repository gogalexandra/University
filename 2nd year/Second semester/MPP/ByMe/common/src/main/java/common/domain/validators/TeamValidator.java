package common.domain.validators;


import common.domain.Team;

public class TeamValidator implements IValidator<Team> {

    @Override
    public void validate(Team entity) throws ValidatorException{
        if (entity.getId() == null) {
            throw new ValidatorException("Id cannot be null!");
        }
        if (entity.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        }
        if (entity.getTeamPrincipalId() == null) {
            throw new ValidatorException("TeamPrincipalId cannot be null!");
        }
    }
}