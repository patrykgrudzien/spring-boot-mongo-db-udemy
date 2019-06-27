package com.jurik99.persistence;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.jurik99.model.LegoSet;

// to be scanned by (mongobee)
@ChangeLog(order = "001")
public class DataMigrations {

	@ChangeSet(order = "001", author = "patryk.grudzien", id = "update number of parts")
	public void updateNbParts(final MongoTemplate mongoTemplate) {

		final Criteria priceZeroCriteria = new Criteria().orOperator(
				Criteria.where("nbParts").is(0),
				Criteria.where("nbParts").is(null)
		);

		mongoTemplate.updateMulti(
				new Query(priceZeroCriteria),
				Update.update("nbParts", 122),
				LegoSet.class
		);

		System.out.println("Applied changeset 001");
	}
}
