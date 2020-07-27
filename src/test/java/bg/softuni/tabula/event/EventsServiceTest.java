package bg.softuni.tabula.event;

import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.model.EventType;
import bg.softuni.tabula.event.repository.EventRepository;
import bg.softuni.tabula.testutil.YearMonthConverter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventsServiceTest {

  @Mock
  EventRepository eventRepository;

  private EventsService serviceToTest;

  @BeforeEach
  public void setUp() {
    serviceToTest = new EventsService(eventRepository);
  }

  @Nested
  @DisplayName("Tests if the events are relevant")
  class IsRelevantTest {

    private Instant eventOccurrance = LocalDateTime.
        of(2020, 8, 15, 12, 32, 45).
        atZone(ZoneId.systemDefault()).
        toInstant();

    private EventEntity testEntity = new EventEntity();

    @BeforeEach
    void setUp() {
      testEntity.setOccurrence(eventOccurrance);
    }

    @Nested
    @DisplayName("Tests annual events")
    class AnnualEventsTest {

      @BeforeEach
      void setUp() {
        testEntity.setEventType(EventType.ANNUALLY);
      }

      @ParameterizedTest(name = "Test {index}: Valid annual event for {0}")
      @CsvSource({"(2020/8)", "(2021/8)", "(2022/8)"})
      void test_AnnualEventRelevant(
          @ConvertWith(YearMonthConverter.class) YearMonth testRequestMonth) {
        boolean actualResult = EventsService.isRelevant(testEntity, testRequestMonth);
        Assertions.assertTrue(actualResult);
      }

      @ParameterizedTest(name = "Test {index}: Invalid annual event for {0}")
      @CsvSource({"(2020/7)", "(2020/9)", "(2019/8)"})
      void test_AnnualEventNotRelevant(
          @ConvertWith(YearMonthConverter.class) YearMonth testRequestMonth) {
        boolean actualResult = EventsService.isRelevant(testEntity, testRequestMonth);
        Assertions.assertFalse(actualResult);
      }

      @DisplayName("Test leap year")
      @Test
      void test_LeapYear() {
        Instant february_29 = LocalDateTime.
            of(2020, 2, 29, 12, 32, 45).
            atZone(ZoneId.systemDefault()).
            toInstant();
        testEntity.setOccurrence(february_29);

        YearMonth leap_2020 = YearMonth.of(2020, 2);
        YearMonth leap_2024 = YearMonth.of(2024, 2);
        YearMonth non_leap_2021 = YearMonth.of(2021, 2);

        Assertions.assertTrue(EventsService.isRelevant(testEntity, leap_2020));
        Assertions.assertTrue(EventsService.isRelevant(testEntity, leap_2024));
        Assertions.assertFalse(EventsService.isRelevant(testEntity, non_leap_2021));
      }
    }
  }
}
