package com.jurik99.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.jurik99.model.DeliveryInfo;
import com.jurik99.model.LegoSet;
import com.jurik99.model.LegoSetDifficulty;
import com.jurik99.model.ProductReview;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DbSeeder implements CommandLineRunner {

	@Override
	public void run(final String... args) {

		final LegoSet millenniumFalcon = new LegoSet(
				"Millennium Falcon",
				"Star Wars",
				LegoSetDifficulty.HARD,
				new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
				Arrays.asList(
						new ProductReview("Dan", 7),
						new ProductReview("Anna", 10),
						new ProductReview("John", 8)
				)
		);

		final LegoSet skyPolice = new LegoSet(
				"Sky Police Air Base",
				"City",
				LegoSetDifficulty.MEDIUM,
				new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
				Arrays.asList(
						new ProductReview("Dan", 5),
						new ProductReview("Andrew", 8)
				)
		);

		final LegoSet mcLarenSenna = new LegoSet(
				"McLaren Senna",
				"Speed Champions",
				LegoSetDifficulty.EASY,
				new DeliveryInfo(LocalDate.now().plusDays(10), 100, false),
				Arrays.asList(
						new ProductReview("Bogdan", 9),
						new ProductReview("Christa", 9)
				)
		);

		final LegoSet mindstormEve = new LegoSet(
				"MINDSTORMS EV3",
				"Mindstorms",
				LegoSetDifficulty.HARD,
				new DeliveryInfo(LocalDate.now().plusDays(10), 100, false),
				Arrays.asList(
						new ProductReview("Cosmin", 10),
						new ProductReview("Jane", 9),
						new ProductReview("James", 10)
				)
		);
	}
}
