package com.huawei.trip.customer;

import com.huawei.trip.entry.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Customer implements CompanionList, CommercialsLimit {
  private Name name;
  private Address address;
  private Birthday birthday;
  private Passport passport;
  private Resident resident;
  private VisitedCountries countries;
  private Flight flight;
  private Commercials commercials = new Commercials(0);

  private ChoiceList choices = new ChoiceList();
  private BusinessTrip businessTrip = BusinessTrip.no();
  private List<Customer> companions = new ArrayList<>();

  public Customer name(String firstName, String lastName) {
    this.name = new Name(firstName, lastName);
    return this;
  }

  public Customer address(String address, String city, String state) {
    this.address = new Address(address, city, state);
    return this;
  }

  public Customer birthday(LocalDate birthday) {
    this.birthday = new Birthday(birthday);
    return this;
  }

  public Customer passport(String passportNumber, String passportIssuePlace) {
    this.passport = new Passport(passportNumber, passportIssuePlace);
    return this;
  }

  public Customer countryOfResident(String countryOfResident) {
    this.resident = new Resident(countryOfResident);
    return this;
  }

  public Customer countriesVisited(List<String> countries) {
    this.countries = new VisitedCountries(countries);
    return this;
  }

  public Customer flightNumber(String flightNumber) {
    this.flight = new Flight(flightNumber);
    return this;
  }

  public Customer commercials(double value) {
    this.commercials = new Commercials(value);
    return this;
  }

  public Customer businessTrip() {
    this.businessTrip = BusinessTrip.of();
    return this;
  }

  public Customer choices(int value) {
    choices.enable(value);
    return this;
  }

  public Customer addCompanion(Customer companion) {
    companions.add(companion);
    return this;
  }

  private Rule rule(Regulation regulation) {
    return Rule.all(
      birthday.rule(regulation, address, this),
      businessTrip.rule(commercials, this),
      choices.rule(regulation)
    );
  }

  public boolean approved(Regulation regulation) {
    return rule(regulation).approved();
  }

  @Override
  public String toString() {
    StringBuilder buff = new StringBuilder();
    name.describeTo(buff);
    birthday.describeTo(buff);
    passport.describeTo(buff);
    resident.describeTo(buff);
    countries.describeTo(buff);
    flight.describeTo(buff);
    choices.describeTo(buff);
    commercials.describeTo(buff);
    return buff.toString();
  }

  @Override
  public Rule accept(Address address) {
    Predicate<Customer> matched= companion ->
        companion.birthday.isAdult() &&
        companion.address.equals(address);
    return () -> companions.stream().anyMatch(matched);
  }

  @Override
  public Rule passed(Regulation regulation) {
    return () -> companions.stream()
      .map(customer -> customer.rule(regulation))
      .allMatch(Rule::approved);
  }

  @Override
  public Rule upper(double limit) {
    double total = companions.stream()
      .mapToDouble(companion -> companion.commercials.value())
      .reduce(commercials.value(), Double::sum);
    return () -> total <= limit;
  }
}
