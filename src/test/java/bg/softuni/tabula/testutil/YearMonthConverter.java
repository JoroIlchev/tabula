package bg.softuni.tabula.testutil;

import java.time.YearMonth;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class YearMonthConverter implements ArgumentConverter {

  @Override
  public Object convert(Object source, ParameterContext context)
      throws ArgumentConversionException {
    String[] yearMonth = source.
        toString().
        replace("(", "").
        replace(")", "").
        split("/");

    return YearMonth.of(Integer.parseInt(yearMonth[0]), Integer.parseInt(yearMonth[1]));
  }
}
