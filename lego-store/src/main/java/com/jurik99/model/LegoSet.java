package com.jurik99.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.jurik99.MongoConstants;
import com.jurik99.persistence.DbSeeder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@Setter(value = AccessLevel.NONE)
@Document(collection = MongoConstants.LEGO_SETS_COLLECTION_NAME)
public class LegoSet {

	@Id
	private String id;

	@TextIndexed
	private String name;

	private LegoSetDifficulty difficulty;

	/**
	 * Indexing helps retrieving data faster but it also adds penalty when inserting, updating, removing data.
	 * So avoid using too much (don't index each field) because if will decrease performance.
	 *
	 * Even with {@link Indexed} annotation,
     * {@link MongoTemplate} during inserting all documents inside {@link DbSeeder#run(String...)}
     * doesn't create that index automatically!
     * The easiest solution is to use {@link MongoRepository}.
	 */
	@Indexed(direction = IndexDirection.ASCENDING)
    @TextIndexed
	private String theme;

	private Collection<ProductReview> reviews = new ArrayList<>();

	@Field(value = "delivery")
	private DeliveryInfo deliveryInfo;

	@Transient
	private int nbParts;

	@PersistenceConstructor // used to point mongodb which constructor to use (if multiple) for serialization/deserialization
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
