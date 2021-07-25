package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Player;
import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;


/**
 * 
 * @author Rajanikant Yadav
 *
 */
@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Token SaveToken(Token token)
	{
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
	    
	    String accessToken = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    token.setGenerateTimeStamp(timeStamp);
	    
	    token.setAccessToken(accessToken);
	    	
		return tokenRepository.save(token);
	}
	
	public Token findTokenByTokenId(Long tokenId)
	{
		return tokenRepository.findTokenByTokenId(tokenId);
	}
	
	
	public List<Token> findAllToke()
	{
		
		
		return (List<Token>) tokenRepository.findAll();
	}
	
	public boolean existsByPlayerID(Player player)
	{
		
		if (player!=null && tokenRepository.existsByPlayerID(player.getPlayerId())) {
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public Token getTokenByPlayerID(Player player)
	{
		
		if (player!=null && tokenRepository.existsByPlayerID(player.getPlayerId())) {
			return tokenRepository.findTokenByPlayerID(player.getPlayerId());
		}
		else
		{
			return null;
		}
		
	}
	
	public boolean existsByAccessToken(String  accessToken)
	{
		

		if (accessToken!=null && tokenRepository.existsByAccessToken(accessToken)) {
			return true;
		}
		else
		{
			return false;
		}
	}
}
