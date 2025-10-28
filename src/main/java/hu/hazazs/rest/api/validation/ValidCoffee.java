package hu.hazazs.rest.api.validation;

import hu.hazazs.rest.api.entity.postgres.Coffee;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCoffee.ValidCoffeeValidator.class)
@SuppressWarnings("unused")
public @interface ValidCoffee {

    String DEFAULT_ERROR_MESSAGE = "Not a valid Coffee.";
    String NOT_VALID_ID = "The ID doesn't match the pattern.";
    String NOT_VALID_NAME = "The name doesn't start with the prefix.";
    String message() default DEFAULT_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String idPattern();
    String namePrefix();

    class ValidCoffeeValidator implements ConstraintValidator<ValidCoffee, Coffee> {

        private String idPattern;
        private String namePrefix;

        @Override
        public void initialize(ValidCoffee constraint) {
            idPattern = constraint.idPattern();
            namePrefix = constraint.namePrefix();
        }

        @Override
        public boolean isValid(Coffee coffee, ConstraintValidatorContext context) {
            context.disableDefaultConstraintViolation();

            boolean validId = coffee.getId().matches(idPattern);
            if (!validId) {
                context.buildConstraintViolationWithTemplate(NOT_VALID_ID)
                        .addConstraintViolation();
            }

            boolean validName = coffee.getName().startsWith(namePrefix);
            if (!validName) {
                context.buildConstraintViolationWithTemplate(NOT_VALID_NAME)
                        .addConstraintViolation();
            }

            return validId && validName;
        }

    }

}