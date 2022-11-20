package com.mockito.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
public class Payment {

    @NotBlank(message = "Order id cannot be blank")
    private String orderId;

    @Range(min = 10_000L, max = 100_000_000L, message = "Amount must between 10.000 and 100.000.000")
    @NotNull(message = "Amount cannot be null")
    private long amount;

    @NotBlank(message = "Credit cart number cannot be blank")
    @LuhnCheck(message = "Invalid credit card number!!!")
    private String creditCard;
}
