package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter(AccessLevel.NONE)
@Document
public class PaymentOption {

	@Id
	private String id;
	private final PaymentType paymentType;
	private final int fee;
}
