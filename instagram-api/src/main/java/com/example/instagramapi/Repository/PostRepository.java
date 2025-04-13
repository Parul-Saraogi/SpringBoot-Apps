package com.example.instagramapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.instagramapi.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	
	//Find BY User id =to get all post aby userIds
	//Means Getting post by userids
//	@Query("select p from post p where p.user.id=?1")
//	public List<Post>  findByUserId(Integer userId);
//	
//	//Passing Multiple Userids and it should be Sorted by desc order
//	
//	@Query("select p from post p where p.user.id IN : users   ORDER BY p.createdAt Desc")
//	public List<Post>  findAllPostsUserIds(@Param("users") List<Integer> userIds);

	 // Find posts by a single user ID
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findByUserId(Integer userId);

    // Find posts by multiple user IDs, sorted by creation date in descending order
    @Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdBy DESC")
    List<Post> findAllPostsUserIds(@Param("users") List<Integer> userIds);

    @Query("SELECT p FROM Post p JOIN p.comments c WHERE c.id = :commentId")
    Post findByCommentId(@Param("commentId") Integer commentId);

}
