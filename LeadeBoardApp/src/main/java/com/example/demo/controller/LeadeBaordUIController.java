package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@Controller
public class LeadeBaordUIController {

	
	@Autowired
	private PlayerService playerService;

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping("/")
	public String loadUI(@RequestParam(value = "action", required = false) String action,Model model) throws IOException {

		System.out.println("Action : "+action);
		if(action!=null && action.equals("Register"))
		{
			System.out.println("In  Registration: "+action);
			return "Registration";
		}else  if(action!=null && action.equals("GenerateToken"))
		{
			System.out.println("In  GenerateToken: "+action);
			return "GenerateToken";
		} else if(action!=null && action.equals("SubmitPalindrom"))
		{
			System.out.println("In  SubmitText: "+action);
			return "SubmitText";
		}
		 else if(action!=null && action.equals("GetScore"))
			{
				System.out.println("In  GetTextForMe: "+action);
				return "GetTextForMe";
			}
		 else if(action!=null && action.equals("GetTop10"))
			{
				System.out.println("In  GetTextForTop: "+action);
				return "GetTextForTop";
			}
		else 
			return "uploadForm";



		/*
		 * model.addAttribute("files", storageService.loadAll().map( path ->
		 * MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
		 * "serveFile", path.getFileName().toString()).build().toUri().toString())
		 * .collect(Collectors.toList()));
		 * 
		 * return "uploadForm";
		 */
	}

	@PostMapping("/UI/registerPlayer")
	public String registerPlayer(@RequestParam(value = "Registration", required = false) String action,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "Age", required = false) String PlayerAge,
			@RequestParam(value = "emailId", required = false) String playerEmailId
			,Model model,RedirectAttributes redirectAttributes) throws IOException 
	{
		Player player=new Player();
		player.setPlayerName(playerName);
		player.setPlayerAge(PlayerAge);
		player.setEmailId(playerEmailId);
		System.out.println("Save Player the Player in Controller...."+player);
		if(player!=null && !playerService.checkIfPlayerAlreadyExist(player))
		{
			player= playerService.savePlayer(player);
		
			 redirectAttributes.addFlashAttribute("message",
						"Registration Is Successfull for User '" + playerName + "' and emaild Id '"+playerEmailId+ "' Please Note Player Id ='"+player.getPlayerId()+"'  for next communication !");
		}
		else
		{
		
			redirectAttributes.addFlashAttribute("message",
					"Player already exsit with same email id  " + playerEmailId + "!");
		}
		
		

		return "redirect:/";
	}

	@PostMapping("/UI/generateToken")
	public String generateToken(@RequestParam(value = "GenerateToken", required = false) String action,
			@RequestParam(value = "playerId", required = false) String playerId,
			@RequestParam(value = "emailId", required = false) String emailId
			,Model model,RedirectAttributes redirectAttributes) throws IOException 
	{

		Player player=new Player();
		player.setPlayerId(Long.parseLong(playerId));
		player.setEmailId(emailId);
	
		Token token=new Token();

		if(player!=null && tokenService.existsByPlayerID(player))
		{
			Token oldtoken= tokenService.getTokenByPlayerID(player);

			redirectAttributes.addFlashAttribute("message",
					"Player with PlayerId= "+ oldtoken.getPlayerID()+"  already have Token with same Token id  = " + oldtoken.getAccessToken() + "!");
			 
		}
		else
		{   
			token.setPlayerID(player.getPlayerId());
			token= tokenService.SaveToken(token);
			 

			redirectAttributes.addFlashAttribute("message",
					"Token Generated Successfull for Player " + token.getPlayerID() + " TokenId is "+token.getAccessToken()+" !");
			
		}
		
		return "redirect:/";
	}


	@PostMapping("/UI/getTokenForPlayer")
	public void getTokenForPlayer(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "action", required = false) String playerName,
			@RequestParam(value = "action", required = false) String PlayerAge,
			@RequestParam(value = "action", required = false) String playerEmailId
			,Model model) throws IOException 
	{

		
	}

	@PostMapping("/UI/submitText")
	public String submitText(@RequestParam(value = "submitText", required = false) String action,
			@RequestParam(value = "playerId", required = false) String playerId,
			@RequestParam(value = "tokenId", required = false) String tokenId,
			@RequestParam(value = "text", required = false) String text
			,Model model,RedirectAttributes redirectAttributes) throws IOException 
	{

		Submission submission = new Submission();
		
		submission.setPlayerID(Long.parseLong(playerId));
		submission.setAccessToken(tokenId);
		submission.setText(text);
		
		
		boolean validSubmission=false;
		if(submission!=null)
		{
			if(submission.getText()!=null && submission.getText().trim()=="" || !submissionService.validate(submission.getText()))
			{
				submission.setStatus("Failure");
				submission.setMessage("Text is not valid .There are repeated character!");
				
				redirectAttributes.addFlashAttribute("message",
						"Text Submitted '" + submission.getText() + "' is not valid .There are repeated character!");
				
				return "redirect:/";
			}
			else if(submission.getText()!=null && submission.getText().trim()!="" && !submissionService.isPalindrome(submission.getText()))
			
			{
				submission.setStatus("Failure");
				submission.setMessage("Text is not valid Palindrom!");
				
				redirectAttributes.addFlashAttribute("message",
						"Text Submitted '" + submission.getText() + "' not valid Palindrom!");
				
				return "redirect:/";
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
			
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Token'" + submission.getAccessToken() + "' . Token Does Not Exist for the user!");
		
			return  "redirect:/";
		}
		
		if(submission!=null && playerService.findPlayerByPlayerId(submission.getPlayerID())!=null &&!tokenService.existsByAccessToken(submission.getAccessToken()))
		{
			submission.setStatus("Failure");
			submission.setMessage("Token Does Not Exist for the user!");
			
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Token'" + submission.getAccessToken() + "' . Token Does Not Exist for the user!");
			
			return  "redirect:/";
		}
		if(submission!=null && playerService.findPlayerByPlayerId(submission.getPlayerID())!=null &&tokenService.existsByAccessToken(submission.getAccessToken())&& validSubmission)
		{
			System.out.println("Save submission the submission in Controller....");

			submission.setStatus("Success");
			submission.setMessage("Submissonwas successfull");
			submission.setScore(submissionService.calculateScoreFromSubmission(submission));
			
			redirectAttributes.addFlashAttribute("message",
					"Text '" + submission.getText() + "' submmittes SuccessFully. Score for this submissio is : "+submission.getScore());
			submissionService.SaveSubmission(submission);
			return  "redirect:/";
		}
		else
		{   submission.setStatus("Failed!");
			submission.setMessage("Submission Failed with unknown Reason");
			
			redirectAttributes.addFlashAttribute("message",
					"Text Submmision for Text '"+submission.getText() +"'  failed with unknow reason ");
			
			return  "redirect:/";
		}
	}


	@PostMapping("/UI/getSubmittedText")
	public String getSubmitTextForAPlayer(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "playerId", required = false) String playerId,
			@RequestParam(value = "tokenId", required = false) String tokenId
					,Model model,RedirectAttributes redirectAttributes) throws IOException 
	{
		System.out.println(" playerId "+playerId);
		
		System.out.println(" tokenId "+tokenId);
		
		if(playerId==null || tokenId== null)
		{
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Player Id '" + playerId + "' . Player ID Or Token iD Provided  Does Not Exist for the Player!");
		
			return  "redirect:/";
		}
		
		if(playerId!=null && playerService.findPlayerByPlayerId(Long.parseLong(playerId))==null)
		{
			
			
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Player Id '" + playerId + "' . Player ID  Does Not Exist for the Player!");
		
			return  "redirect:/";
		}
		
		if(tokenId!=null && playerService.findPlayerByPlayerId(Long.parseLong(playerId))!=null &&!tokenService.existsByAccessToken(tokenId))
		{
		
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Token'" + tokenId + "' . Token Does Not Exist for the user!");
			
			return  "redirect:/";
		}
		
		List<ResponseSubmissionTemplateVo>  listofSubmission=new ArrayList<ResponseSubmissionTemplateVo>();
		List<Submission> submission=submissionService.findSubmmisionByPlayer(Long.parseLong(playerId));
		for(Submission submit:submission)
		{
						
			System.out.println("Iteratine result");
			Long foundplayerId=submit.getPlayerID();
			System.out.println("foundplayerId result"+foundplayerId);
			ResponseSubmissionTemplateVo reponsevo=new ResponseSubmissionTemplateVo();
			reponsevo.setPlayer(playerService.findPlayerByPlayerId(foundplayerId));
			reponsevo.setSubmission(submit);
			listofSubmission.add(reponsevo);
		}
		model.addAttribute("submissionDetails", listofSubmission);
		
		return  "GetTextForMeResult";

	}
	
	@PostMapping("/UI/getSubmittEdTextTop")
	public String  getSubmitTextForTopPlayer(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "playerId", required = false) String playerId,
			@RequestParam(value = "tokenId", required = false) String tokenId
					,Model model,RedirectAttributes redirectAttributes) throws IOException 
	{

		
		if(playerId!=null && playerService.findPlayerByPlayerId(Long.parseLong(playerId))==null)
		{
			
			
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Player Id '" + playerId + "' . Player ID  Does Not Exist for the Player!");
		
			return  "redirect:/";
		}
		
		if(tokenId!=null && playerService.findPlayerByPlayerId(Long.parseLong(playerId))!=null &&!tokenService.existsByAccessToken(tokenId))
		{
		
			redirectAttributes.addFlashAttribute("message",
					"Failure for  Token'" + tokenId + "' . Token Does Not Exist for the user!");
			
			return  "redirect:/";
		}
		
		
		List<ResponseSubmissionTemplateVo>  listofSubmission=new ArrayList<ResponseSubmissionTemplateVo>();

	
		
	
		List<Submission> submission=submissionService.findTop10Submision();
		for(Submission submit:submission)
		{
			System.out.println("Iteratine result");
			Long foundplayerId=submit.getPlayerID();
			System.out.println("foundplayerId result"+foundplayerId);
			ResponseSubmissionTemplateVo reponsevo=new ResponseSubmissionTemplateVo();
			reponsevo.setPlayer(playerService.findPlayerByPlayerId(foundplayerId));
			reponsevo.setSubmission(submit);
			listofSubmission.add(reponsevo);
		}
		
		
	   model.addAttribute("submissionDetails", listofSubmission);
		
		return  "GetTextForTopResult";
	}


}
