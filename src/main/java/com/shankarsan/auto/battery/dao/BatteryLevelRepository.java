package com.shankarsan.auto.battery.dao;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;

@Repository
public interface BatteryLevelRepository {
	
	FindIterable<Document> getBatteryLevel(String levelId, String passcode);
	Document saveBatteryLevel(String levelId, String passcode, int status, int level);

}
