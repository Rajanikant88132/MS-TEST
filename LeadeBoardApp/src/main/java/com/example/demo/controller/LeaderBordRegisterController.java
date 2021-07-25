package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Player;
import com.example.demo.entity.ResponseSubmissionTemplateVo;
import com.example.demo.entity.Submission;
import com.example.demo.entity.Token;
import com.example.demo.service.PlayerService;
import com.example.demo.service.SubmissionService;
import com.example.demo.service.TokenService;

/**
 * 
 * @author Rajanikant Yadav
 *
 */

@RestController
@RequestMapping("/LeaderBoard")
public class LeaderBordRegisterController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/player/getplayerById/{playerId}")
	public Player findPlayerById(@PathVariable("playerId") Long playerId)
	{
		System.out.println("findPlayerById the Department in Controller....");
		return playerService.findPlayerByPlayerId(playerId);
	}

	@PostMapping("/player/Saveplayer/")
	public Player savePlayer(@RequestBody Player player)
	{

		System.out.println("Save Player the Player in Controller...."+player);
		if(player!=null && !playerService.checkIfPlayerAlreadyExist(player))
		{
			return playerService.savePlayer(player);
		}
		else
		{
		
			return playerService.findByEmailId(player);
		}
	}


	@PostMapping("/token/getToken/")
	public Token getTokenByPlayer(@RequestBody Player player)
	{
		System.out.println("findTokenIdById the TokenId in Controller....");
		if(player!=null && playerService.checkIfPlayerAlreadyExist(player))
		{
			return tokenService.getTokenByPlayerID(player);
		}
		return null;
	}

	@PostMapping("/token/GenerateToken/")
	public Token saveToken(@RequestBody Player player)
	{
		Token token=new Token();

		if(player!=null && tokenService.existsByPlayerID(player))
		{
			return tokenService.getTokenByPlayerID(player);
		}
		else
		{

			token.setPlayerID(player.getPlayerId());
		}
		return tokenService.SaveToken(token);
	}


	@GetMapping("/submission/getSubmissionById/{submissionId}")
	public Submission getSubmissionById(@PathVariable("submissionId") Long submissionId)
	{
		System.out.println("findsubmissionIdIdById the submissionId in Controller....");

		return submissionService.findSubmissionBySubmissionId(submissionId);
	}

	@PostMapping("/submission/SaveSubmission/")
	public Submission saveSubmission(@RequestBody Submission submission)
	{
		boolean validSubmission=false;
		if(submission!=null)
		{
			if(submission.getText()!=null && submission.getText().trim()=="" || !submissionService.validate(submission.getText()))
			{
				submission.setStatus("Failure");
				submission.setMessage("Text is not valid .There are repeated character!");
				return submission;
			}
			else if(submission.getText()!=null && submission.getText().trim()!="" && !submissionService.isPalindrome(submission.getText()))
			
			{
				submission.setStatus("Failure");
				submission.setMessage("Text is not valid Palindrom!");
				return submission;
			}
			else
			{
				validSubmission=true;
				
			}


		}

		if(submission!=null && playerService.findPlayerByPlayerId(submission.getPlayerID())==null)
		{
			submission.setStatus("Failure");
			submission.setMessage("Player Does Not Exist!");
			return submission;
		}
		
		if(submission!=null && playerService.findPlayerByPlayerId(submission.getPlayerID())!=null &&!tokenService.existsByAccessToken(submission.getAccessToken()))
		{
			submission.setStatus("Failure");
			submission.setMessage("Token Does Not Exist for the user!");
			return submission;
		}
		if(submission!=null && playerService.findPlayerByPlayerId(submission.getPlayerID())!=null &&tokenService.existsByAccessToken(submission.getAccessToken())&& validSubmission)
		{
			System.out.println("Save submission the submission in Controller....");

			submission.setStatus("Success");
			submission.setMessage("Submissonwas successfull");
			submission.setScore(submissionService.calculateScoreFromSubmission(submission));
			return submissionService.SaveSubmission(submission);
		}
		else
		{   submission.setStatus("Failed!");
			submission.setMessage("Submission Failed with unknown Reason");
			return submission;
		}
	}
	
	@PostMapping("/submission/getSubmissionByPlayer/")
	public List<ResponseSubmissionTemplateVo> getSubmissionForPlayer(@RequestBody Token token)
	{
		List<ResponseSubmissionTemplateVo>  listofSubmission=new ArrayList<ResponseSubmissionTemplateVo>();
		List<Submission> submission=submissionService.findSubmmisionByPlayer(token.getPlayerID());
		for(Submission submit:submission)
		{
			Long playerId=submit.getPlayerID();
			ResponseSubmissionTemplateVo reponsevo=new ResponseSubmissionTemplateVo();
			reponsevo.setPlayer(playerService.findPlayerByPlayerId(playerId));
			reponsevo.setSubmission(submit);
			listofSubmission.add(reponsevo);
		}
		return listofSubmission;
	}
	
	@PostMapping("/submission/getTopsubmission/")
	public List<ResponseSubmissionTemplateVo> findTop10Submision(@RequestBody Token token)
	{
		List<ResponseSubmissionTemplateVo>  listofSubmission=new ArrayList<ResponseSubmissionTemplateVo>();

		if(token!=null && playerService.findPlayerByPlayerId(token.getPlayerID())!=null &&!tokenService.existsByAccessToken(token.getAccessToken()))
		{
			
			return listofSubmission;
		}
		
		
	
		List<Submission> submission=submissionService.findTop10Submision();
		for(Submission submit:submission)
		{
			Long playerId=submit.getPlayerID();
			ResponseSubmissionTemplateVo reponsevo=new ResponseSubmissionTemplateVo();
			reponsevo.setPlayer(playerService.findPlayerByPlayerId(playerId));
			reponsevo.setSubmission(submit);
			listofSubmission.add(reponsevo);
		}
		return listofSubmission;
	}
}
