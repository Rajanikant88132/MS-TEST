package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rajanikant Yadav
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOKEN")
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long tokenId;
    String accessToken;
    Long playerID;
    String generateTimeStamp;
    
    @JsonProperty(value = "tokenId")
	public Long getTokenId() {
		return tokenId;
	}
    
    @JsonProperty(value = "tokenId")
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}
    
    @JsonProperty(value = "accessToken")
	public String getAccessToken() {
		return accessToken;
	}
    
    @JsonProperty(value = "accessToken")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
    
    @JsonProperty(value = "playerID")
	public Long getPlayerID() {
		return playerID;
	}
    
    @JsonProperty(value = "playerID")
	public void setPlayerID(Long playerID) {
		this.playerID = playerID;
	}
    
    @JsonProperty(value = "generateTimeStamp")
	public String getGenerateTimeStamp() {
		return generateTimeStamp;
	}
	public void setGenerateTimeStamp(String generateTimeStamp) {
		this.generateTimeStamp = generateTimeStamp;
	}

}
