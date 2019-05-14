package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

import static com.huawei.trip.util.Preconditions.allowEmpty;
import static com.huawei.trip.util.Preconditions.requireNonEmpty;

public class Name implements SelfDescribing {
  private String firstName;
  private String lastName;
  private String middleName;

  public Name(String firstName, String lastName) {
    this(firstName, lastName, "");
  }

  public Name(String firstName, String lastName, String middleName) {
    this.firstName = requireNonEmpty(firstName, "first name can't be blank");
    this.lastName = requireNonEmpty(lastName, "last name can't be blank");
    this.middleName = allowEmpty(middleName);
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Application: " + firstName + " " + middleName + " " + lastName + "\n");
  }
}