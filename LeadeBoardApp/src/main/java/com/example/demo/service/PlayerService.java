package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;

/**
 * 
 * @author Rajanikant Yadav
 *
 */

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;

	public Player savePlayer(Player player)
	{
		System.out.println("Save the player in Service...."+player);
		
		return playerRepository.save(player);
	}
	
	public Player findPlayerByPlayerId(Long playerId)
	{
		System.out.println("getPlayer the Player in Service...."+playerRepository.findPlayerByPlayerId(playerId));
		
		
		return playerRepository.findPlayerByPlayerId(playerId);
	}
	
	public List<Player> findAllPlayer()
	{
		
		
		return (List<Player>) playerRepository.findAll();
	}
	
	public boolean checkIfPlayerAlreadyExist(Player player)
	{
		
		if (playerRepository.existsByEmailId(player.getEmailId())) {
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Player findByEmailId(Player player)
	{
		
		
		Player oldplayer =playerRepository.findByEmailId(player.getEmailId());
	
		return oldplayer;
	}
}
