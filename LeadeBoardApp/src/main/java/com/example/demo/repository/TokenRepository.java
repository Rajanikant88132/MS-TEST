package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Token;

/**
 * 
 * @author Rajanikant Yadav
 *
 */

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {
	
	public Token findTokenByTokenId(Long tokenId );

	Boolean existsByAccessToken(String accessToken);

	public Token findTokenByPlayerID(Long tokenId );
	public boolean existsByPlayerID(Long tokenId );

}