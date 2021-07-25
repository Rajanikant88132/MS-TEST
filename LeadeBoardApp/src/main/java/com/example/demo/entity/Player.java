package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "PLAYER")
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PLAYER_SEQUENCE")
	@SequenceGenerator(name= "PLAYER_SEQUENCE", sequenceName = "PLAYER_SEQUENCE_ID", allocationSize = 1)
	@Column(name = "player_Id")
	Long playerId;
	
	@Column(name = "player_Name")
    String playerName;
	
	@Column(name = "player_Age")
    String playerAge;
	
	@Column(name = "Email_Id")
    String emailId;
	
	@Column(name = "total_score")

	Long totalScore;
	
	
	 @JsonProperty(value = "playerId")
    public Long getPlayerId() {
		return playerId;
	}
		
	 @JsonProperty(value = "playerId")
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

		
	 @JsonProperty(value = "playerName")
	public String getPlayerName() {
		return playerName;
	}

	 @JsonProperty(value = "playerName")
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	 @JsonProperty(value = "playerAge")
	public String getPlayerAge() {
		return playerAge;
	}

	 @JsonProperty(value = "playerAge")
	public void setPlayerAge(String playerAge) {
		this.playerAge = playerAge;
	}

	 @JsonProperty(value = "emailId")
	public String getEmailId() {
		return emailId;
	}

	 @JsonProperty(value = "emailId")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	 @JsonProperty(value = "totalScore")
	public Long getTotalScore() {
		return totalScore;
	}

	 @JsonProperty(value = "totalScore")
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

    
    public String toString()
	{
		return " PLAYER  :"+playerId+" ,"+playerName+","+playerAge+","+emailId+" ,"+totalScore;
	}
}
