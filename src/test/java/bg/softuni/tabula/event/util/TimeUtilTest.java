package bg.softuni.tabula.event.util;

import bg.softuni.tabula.testutil.YearMonthConverter;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

public class TimeUtilTest {

  @ParameterizedTest(name = "Test {index}: In {0} the first day of week is {1}")
  @CsvSource({"(2020/7),2", "(2020/8),5", "(2020/9),1"})
  void test_GetFirstDayInWeek(
      @ConvertWith(YearMonthConverter.class) YearMonth testYearMonth, int expectedResult) {
    int actualResult = TimeUtil.getFirstDayInWeek(testYearMonth);
    Assertions.assertEquals(expectedResult, actualResult);
  }


  @ParameterizedTest(name = "Test {index}: In {0} the number of days is {1}")
  @CsvSource({"(2020/7),31", "(2020/8),31", "(2020/9),30"})
  void test_GetDaysInMonth(@ConvertWith(YearMonthConverter.class) YearMonth testYearMonth, int expectedResult) {
    int actualResult = TimeUtil.getDaysInMonth(testYearMonth);
    Assertions.assertEquals(expectedResult, actualResult);
  }
}
