package com.awbd.booking.services;

import com.awbd.booking.model.Discount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "discount")
public interface DiscountServiceProxy {
    @GetMapping("/discount")
    Discount findDiscount();
}
