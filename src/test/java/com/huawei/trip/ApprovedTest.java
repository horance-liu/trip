package com.huawei.trip;

import com.huawei.trip.customer.Customer;
import com.huawei.trip.customer.Regulation;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ApprovedTest {
  Regulation regulation = new Regulation();

  Customer parent = new Customer()
    .name("San", "Zhang")
    .address("200 Main Street", "Chicago", "IL")
    .birthday(LocalDate.of(1975, 1, 1))
    .passport("E47652345","Beijing")
    .countryOfResident("China")
    .countriesVisited(Arrays.asList("China"))
    .flightNumber("CA850");

  Customer child = new Customer()
    .name("Shisan", "Zhang")
    .address("200 Main Street", "Chicago", "IL")
    .birthday(LocalDate.of(1989, 6, 4))  // adult
    .passport("E47652345","Beijing")
    .countryOfResident("China")
    .countriesVisited(Arrays.asList("China"))
    .flightNumber("CA850")
    .addCompanion(parent);

  @Test
  public void passed() {
    assertTrue(parent.approved(regulation));
    assertTrue(child.approved(regulation));
  }
}
