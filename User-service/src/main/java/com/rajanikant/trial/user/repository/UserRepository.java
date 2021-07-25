package com.rajanikant.trial.user.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.rajanikant.trial.user.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

 User findUserByUserId(Long userid);
}
