package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.NONE)
public class ProductReview {

	private final String userName;
	private final int rating;
}
