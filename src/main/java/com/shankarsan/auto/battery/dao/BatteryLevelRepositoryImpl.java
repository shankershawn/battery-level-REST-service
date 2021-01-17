package com.shankarsan.auto.battery.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

@Repository
public class BatteryLevelRepositoryImpl implements BatteryLevelRepository {
	
	@Autowired
	@Qualifier("template")
	private MongoOperations mongoOperations;

	@Override
	public FindIterable<Document> getBatteryLevel(String level_id, String passcode) {
		return mongoOperations
			.getCollection("batterylevel")
			.find(Filters.and(Filters.eq("level_id", level_id), Filters.eq("passcode", passcode))).projection(new BasicDBObject("level", 1));
	}

	@Override
	public Document saveBatteryLevel(String level_id, String passcode, int level) {
		return mongoOperations
			.getCollection("batterylevel")
			.findOneAndUpdate(Filters.and(Filters.eq("level_id", level_id), Filters.eq("passcode", passcode)),
				new Document("$set", new Document().append("level", level)),
				new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
			);
	}

}
