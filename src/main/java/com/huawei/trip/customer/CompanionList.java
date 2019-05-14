package com.huawei.trip.customer;

import com.huawei.trip.entry.Address;

public interface CompanionList {
  Rule accept(Address address);
  Rule passed(Regulation regulation);
}
