package com.jurik99;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jurik99.model.DeliveryInfo;
import com.jurik99.model.LegoSet;
import com.jurik99.model.LegoSetDifficulty;
import com.jurik99.model.PaymentOption;
import com.jurik99.model.ProductReview;
import com.jurik99.persistence.LegoSetRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jurik99.model.PaymentType.CREDIT_CARD;
import static com.jurik99.model.PaymentType.PAY_PAL;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LegoStoreDatabaseTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private LegoSetRepository legoSetRepository;

	@SuppressWarnings("Duplicates")
	@Before
	public void before() {
		// clean-up
		legoSetRepository.deleteAll();

		final LegoSet millenniumFalcon = new LegoSet(
				"Millennium Falcon",
				"Star Wars",
				LegoSetDifficulty.HARD,
				new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
				Arrays.asList(
						new ProductReview("Dan", 7),
						new ProductReview("Anna", 10),
						new ProductReview("John", 8)
				),
				new PaymentOption(CREDIT_CARD, 0)
		);

		final LegoSet skyPolice = new LegoSet(
				"Sky Police Air Base",
				"City",
				LegoSetDifficulty.MEDIUM,
				new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
				Arrays.asList(
						new ProductReview("Dan", 5),
						new ProductReview("Andrew", 8)
				),
				new PaymentOption(PAY_PAL, 7)
		);

		legoSetRepository.insert(millenniumFalcon);
		legoSetRepository.insert(skyPolice);
	}

    @Test
    public void findAllByGreatReviewsShouldReturnProductsThatHaveReviewWithRatingOf10() {
		// when
	    final List<LegoSet> results = new ArrayList<>(legoSetRepository.findAllByGreatReviews());

	    // then
	    assertEquals(1, results.size());
	    assertEquals("Millennium Falcon", results.get(0).getName());
    }

}
