package com.shankarsan.auto.battery.dao;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;

@Repository
public interface BatteryLevelRepository {
	
	FindIterable<Document> getBatteryLevel(String level_id, String passcode);
	Document saveBatteryLevel(String level_id, String passcode, int level);

}
