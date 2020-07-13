package mrs.app.reservation;

import java.time.LocalTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ThirtyMinuteUnitValidator
    implements ConstraintValidator<ThirtyMinutesUnit, LocalTime> {

  @Override
  public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return value.getMinute() % 30 == 0;
  }
}
