package com.rajanikant.trial.user.repository;

import org.springframework.data.jpa.repository.*;
import com.rajanikant.trial.user.entity.*;
public interface UserRepository extends JpaRepository<User,Long>{

 User findUserByUserId(Long userid);
}
