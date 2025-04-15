package com.fit.customerservice.mapper;

import org.springframework.stereotype.Component;

import com.fit.customerservice.dto.CustomerDto;
import com.fit.customerservice.entity.Customer;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress());
    }

    public Customer toEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPhone(),
                customerDto.getAddress());
    }
}