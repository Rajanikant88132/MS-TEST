package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rajanikant Yadav
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubmissionTemplateVo {

	private Player player;
	private Submission submission;
	
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Submission getSubmission() {
		return submission;
	}
	public void setSubmission(Submission submission) {
		this.submission = submission;
	}
	
}
