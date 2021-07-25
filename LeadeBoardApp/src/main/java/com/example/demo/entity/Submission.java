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
@Table(name = "SUBMISSION")
public class Submission  implements Comparable<Submission> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long submissionId;
    Long playerID;
    Long tokenId;
    String accessToken;
   	String text;
   	int score;
    String status;
    String message;
  
	@JsonProperty(value = "accessToken")
    public String getAccessToken() {
		return accessToken;
	}

    @JsonProperty(value = "accessToken")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


    @JsonProperty(value = "submissionId")
    public Long getSubmissionId() {
		return submissionId;
	}


    @JsonProperty(value = "submissionId")
	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}


    @JsonProperty(value = "playerID")
	public Long getPlayerID() {
		return playerID;
	}


    @JsonProperty(value = "playerID")
	public void setPlayerID(Long playerID) {
		this.playerID = playerID;
	}


    @JsonProperty(value = "tokenId")
	public Long getTokenId() {
		return tokenId;
	}


    @JsonProperty(value = "tokenId")
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}


    @JsonProperty(value = "text")
	public String getText() {
		return text;
	}


    @JsonProperty(value = "text")
	public void setText(String text) {
		this.text = text;
	}


    @JsonProperty(value = "score")
	public int getScore() {
		return score;
	}

    @JsonProperty(value = "score")

	public void setScore(int score) {
		this.score = score;
	}

    
    @JsonProperty(value = "status")
    public String getStatus() {
		return status;
	}
    @JsonProperty(value = "status")
	public void setStatus(String status) {
		this.status = status;
	}
    @JsonProperty(value = "message")
	public String getMessage() {
		return message;
	}
    @JsonProperty(value = "message")
	public void setMessage(String message) {
		this.message = message;
	}


    @Override
    public int compareTo(Submission submission) {
        int compareage=((Submission)submission).getScore();
   
        return (int) (this.getScore()-compareage);
    
    }
	public String toString()
   	{
   		return " SUBMISSION  :"+submissionId+" ,"+text+","+playerID+","+score;
   	}
}
