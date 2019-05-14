package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

import static com.huawei.trip.util.Preconditions.requireNonEmpty;

public class Resident implements SelfDescribing {
  private String countryOfResident;

  public Resident(String countryOfResident) {
    this.countryOfResident = requireNonEmpty(countryOfResident, "countryOfResident can't be blank");
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Resident at: " + countryOfResident + "\n");
  }
}
