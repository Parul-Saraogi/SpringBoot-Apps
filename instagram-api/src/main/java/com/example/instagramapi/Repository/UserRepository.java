package com.example.instagramapi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.instagramapi.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByUsername(String userName);
	
//	@Query("select u from User u where u.id In:users ")
//	public List<User> findAllUsersByUsersIds(@Param("users") List<Integer> userIds);
//	
//	@Query("Select DISTINCT u FROM USER u Where u.username Like %:query% OR u.email LIKE %:query%")
//	public List<User> findByQuery(@Param("query") String query);

	
	 @Query("SELECT u FROM User u WHERE u.id IN (:users)")
	    List<User> findAllUsersByUsersIds(@Param("users") List<Integer> userIds);

	    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%")
	    List<User> findByQuery(@Param("query") String query);
	
	    @Modifying
	    @Query("UPDATE User u SET u.isActive = false WHERE u.id = :id")
	    Integer deactivateUserById(@Param("id") Integer id);
	    
	    
}
