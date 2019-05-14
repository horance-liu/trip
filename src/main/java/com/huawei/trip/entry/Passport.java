package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

import static com.huawei.trip.util.Preconditions.requireNonEmpty;

public class Passport implements SelfDescribing {
  private String passportNumber;
  private String passportIssuePlace;

  public Passport(String passportNumber, String passportIssuePlace) {
    this.passportNumber = requireNonEmpty(passportNumber, "passportNumber can't be blank");
    this.passportIssuePlace = requireNonEmpty(passportIssuePlace, "passportIssuePlace can't be blank");
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Passport: " + passportNumber + " Issued at: " + passportIssuePlace + "\n");
  }
}
