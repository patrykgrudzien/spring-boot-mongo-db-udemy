package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@Setter(value = AccessLevel.NONE)
public class LegoSet {

	private String id;
	private String name;
	private LegoSetDifficulty difficulty;
	private String theme;
	private Collection<ProductReview> reviews = new ArrayList<>();
	private DeliveryInfo deliveryInfo;
	private int nbParts;

	public LegoSet(final String name, final String theme, final LegoSetDifficulty difficulty,
	               final DeliveryInfo deliveryInfo, final Collection<ProductReview> reviews) {
		this.name = name;
		this.theme = theme;
		this.difficulty = difficulty;
		this.deliveryInfo = deliveryInfo;
		if (reviews != null) {
			this.reviews = reviews;
		}
	}

	public Collection<ProductReview> getReviews() {
		return Collections.unmodifiableCollection(reviews);
	}
}
