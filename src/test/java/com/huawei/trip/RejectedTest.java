package com.huawei.trip;

import com.huawei.trip.customer.Customer;
import com.huawei.trip.customer.Regulation;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static com.huawei.trip.customer.Regulation.FRUITS_OK;
import static com.huawei.trip.customer.Regulation.MEATS_OK;
import static com.huawei.trip.entry.ChoiceList.BRING_SOIL;
import static org.junit.Assert.assertFalse;

public class RejectedTest {
  // not allow soil
  Regulation regulation = new Regulation(FRUITS_OK | MEATS_OK);

  Customer parent = new Customer()
    .name("San", "Zhang")
    .address("200 Main Street", "Chicago", "IL")
    .birthday(LocalDate.of(1975, 1, 1))
    .passport("E47652345","Beijing")
    .countryOfResident("China")
    .countriesVisited(Arrays.asList("China"))
    .choices(BRING_SOIL)  // should not bring soil
    .flightNumber("CA850");

  Customer child = new Customer()
    .name("Shisan", "Zhang")
    .address("200 Main Street", "Chicago", "IL")
    .birthday(LocalDate.of(2014, 6, 4))  // not adult
    .passport("E47652345","Beijing")
    .countryOfResident("China")
    .countriesVisited(Arrays.asList("China"))
    .flightNumber("CA850")
    .addCompanion(parent);

  @Test
  public void bring_soil_but_regulation_not_allowed() {
    assertFalse(parent.approved(regulation));
  }

  @Test
  public void non_adult_is_not_passed_because_parent_is_not_passed() {
    assertFalse(child.approved(regulation));
  }
}
