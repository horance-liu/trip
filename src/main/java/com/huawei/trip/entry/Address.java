package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

public class Address implements SelfDescribing {
  private String address;
  private String city;
  private String state;

  public Address(String address, String city, String state) {
    this.address = address;
    this.city = city;
    this.state = state;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Address))
      return false;
    Address other = (Address) obj;
    return address == other.address &&
      city == other.city &&
      state == other.state;
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("US Address: " + address + " " + city + " " + state + "\n");
  }
}
