/**
 * 
 */
package com.shankarsan.auto.battery.configuration;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author SHANKARSAN
 *
 */
@Component
public class MongoDBConfiguration {
	
	@Value("${spring.data.mongodb.username}")
	private String username;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.authentication-database}")
	private String authDb;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Bean(value = "credential")
	public MongoCredential getMongoCredential() {
		return MongoCredential.createCredential(this.username, this.authDb, this.password.toCharArray());
	}
	
	@Bean(value = "clientSettings")
	public MongoClientSettings getMongoClientSettings(@Qualifier("credential") MongoCredential credential) {
		return MongoClientSettings
				.builder()
				.credential(credential)
				.applyToSslSettings(builder -> builder.enabled(true))
				.applyToClusterSettings(builder -> builder
					.hosts(Arrays.stream(host.split(",")).map(e -> new ServerAddress(e)).collect(Collectors.toList()))
				)
				.build();
	}
	
	@Bean(value = "client")
	public MongoClient getMongoClient(@Qualifier("clientSettings") MongoClientSettings clientSettings) {
		return MongoClients.create(clientSettings);
	}
	
	@Bean(value = "databaseFactory")
	public MongoDatabaseFactory getMongoDatabaseFactory(@Qualifier("client") MongoClient client) {
		return new SimpleMongoClientDatabaseFactory(client, this.database);
	}
	
	@Bean(value = "template")
	public MongoOperations getMongoTemplate(@Qualifier("databaseFactory") MongoDatabaseFactory databaseFactory) {
		return new MongoTemplate(databaseFactory);
	}
	
}
