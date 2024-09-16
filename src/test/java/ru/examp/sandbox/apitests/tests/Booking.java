package ru.examp.sandbox.apitests.tests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class Booking {
   private String firstName;
   private String lastName;
   private Integer totalprice;
   private Boolean depositpaid;
   private BookingDates bookingDates;
   private String additionalneeds;

}
