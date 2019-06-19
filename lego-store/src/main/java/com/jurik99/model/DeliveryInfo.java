package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter(value = AccessLevel.NONE)
public class DeliveryInfo {

	private final LocalDate deliveryDate;
	private final int deliveryFee;
	private final boolean inStock;
}
