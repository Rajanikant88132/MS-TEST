package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Player;
/**
 * 
 * @author Rajanikant Yadav
 *
 */

@Repository
public interface PlayerRepository extends CrudRepository<Player,Long> {
	
	public Player findPlayerByPlayerId(Long playerId );

	Player findByEmailId(String playerName);

	Boolean existsByPlayerName(String playerName);

	Boolean existsByEmailId(String emailId);
}
