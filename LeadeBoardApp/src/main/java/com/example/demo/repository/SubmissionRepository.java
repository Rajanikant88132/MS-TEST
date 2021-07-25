package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Submission;


/**
 * 
 * @author Rajanikant Yadav
 *
 */
@Repository
public interface SubmissionRepository extends CrudRepository<Submission,Long> {

	public Submission findSubmissionBySubmissionId(Long submissionId );


}