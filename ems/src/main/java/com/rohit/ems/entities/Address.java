package com.rohit.ems.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address extends BaseEntity{

    @Column(name="house_number")
    private Long houseNumber;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private Long zipcode;

    // when one to one
    // put the fk in the child/optional 
    // As employee might or might not have address
    // but an address must belong to atleast one employee
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="emp_id", referencedColumnName = "id", nullable = false, unique = true)
    private Employee employee;

}
