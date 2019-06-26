package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
@Setter(value = AccessLevel.NONE)
public class ProductReview {

    @TextIndexed
	private final String userName;
	private final int rating;
}
