package com.rohit.ems.dtos;

import lombok.Data;

@Data
public class AddressResult {
    private Long houseNumber;
    private String street;
    private String city;
    private String state;
    private Long zipcode;
}
