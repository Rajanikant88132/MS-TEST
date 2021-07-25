package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Submission;
import com.example.demo.repository.SubmissionRepository;


/**
 * 
 * @author Rajanikant Yadav
 *
 */


@Service
public class SubmissionService {

	@Autowired
	private SubmissionRepository submissionRepository;

	public Submission SaveSubmission(Submission submission)
	{
		
		System.out.println("Saving submission : "+submission);
		return submissionRepository.save(submission);

	}

	public Submission findSubmissionBySubmissionId(Long submissionId)
	{
		System.out.println("Finding submission : "+submissionId);
		return submissionRepository.findSubmissionBySubmissionId(submissionId);
	}

	public List<Submission> findAllSubmission()
	{
		
		
		return (List<Submission>) submissionRepository.findAll();
	}
	
	public int calculateScoreFromSubmission(Submission submission)
	{
		
		String text=submission.getText();
	
		return text.length();
	
	}
	
	  public  boolean validate(String words)
	  {

		   words = words.replaceAll("[^a-zA-Z ]", "").toLowerCase().replaceAll("\\s+","");
		   words=words.trim();
		  boolean validstring=true;
		 
		  long count=0;
		  for(int i=0;i<words.length();i++)
		  {
			  char checkChar=words.charAt(i);
		       count = words.chars().filter(ch -> ch == checkChar).count();
		       
		       if(count>words.length()/2)
		 	  { validstring=false;
		    		  System.out.println("Wrong Palidrom");
		    	   break;
		 	
		 	  }
		  }
		  
		  return validstring;
		  
	  }
	  
	  public  boolean isPalindrome(String text){  
		  
		    text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().replaceAll("\\s+","");
		    text=text.trim();
		    StringBuilder sb=new StringBuilder(text);  
		    sb.reverse();  
		    String rev=sb.toString();  
		    if(text.equals(rev)){  
		        return true;  
		    }else{  
		        return false;  
		    }  
		}   
	  
	  
	  public List<Submission> findSubmmisionByPlayer(Long playerID)
	  {
		  List<Submission> submissionList= findAllSubmission();
		  System.out.println("All Submission =" +submissionList.size());
		  List<Submission> playersSubmission = submissionList
				  .stream()
				  .filter(c -> c.getPlayerID() == playerID)
				  .collect(Collectors.toList());
		  List<Submission> sortedList = playersSubmission.stream().sorted(Comparator.comparing(Submission::getScore).reversed()).collect(Collectors.toList());
		  System.out.println("playersSubmission Submission =" +sortedList.size());
		  return sortedList;
	  }
	  
	  
	  public List<Submission> findTop10Submision()
	  {
	     List<Submission> submissionList= findAllSubmission();
	     System.out.println("Submistion Found in All Count =" +submissionList.size());
	     List<Submission> sortedList = submissionList.stream().sorted(Comparator.comparing(Submission::getScore).reversed()).collect(Collectors.toList());

	     List<Submission> contestWinners = sortedList.stream().limit(10).collect(Collectors.toList());
	     
		 return contestWinners;
	  }
}


