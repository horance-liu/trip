package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

import static com.huawei.trip.util.Preconditions.requireNonEmpty;

public class Flight implements SelfDescribing {
  private String flightNumber;

  public Flight(String flightNumber) {
    this.flightNumber = requireNonEmpty(flightNumber, "flightNumber can't be blank");
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Flight: " + flightNumber + "\n");
  }
}
