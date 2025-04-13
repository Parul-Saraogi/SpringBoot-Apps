package com.example.instagramapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagramapi.Exceptions.StoryException;
import com.example.instagramapi.Exceptions.UserException;
import com.example.instagramapi.Model.Post;
import com.example.instagramapi.Model.Story;
import com.example.instagramapi.Service.StoryService;

@RestController
@RequestMapping("/api/story")
public class StoryController {

	@Autowired
	private StoryService storyService;
	
	//	public Story createSStory(Integer userId, Story story) throws UserException {

	
	@PostMapping("/createStory/{userId}")
	public ResponseEntity<Story>createSStory(@RequestBody	Story story, 
			@PathVariable  Integer userId  ){
		
		Story sStory = storyService.createSStory(userId, story);
		return new ResponseEntity<Story>(sStory,HttpStatus.CREATED);
	}
	
	//	public List<Story> findallStoryofUserid(Integer userId, Story story) throws UserException, StoryException {

	
	@GetMapping("/findallStoryofUserid/{userId}")
	public ResponseEntity< List<Story>>findallStoryofUserid(@PathVariable  Integer userId  ) throws UserException, StoryException{

		List<Story> findallStoryofUserid = storyService.findallStoryofUserid(userId);
							
		return new ResponseEntity<List<Story> >(findallStoryofUserid,HttpStatus.OK);
	}
	
	  @PutMapping("/updateStory/{userId}")
	    public ResponseEntity<Story> updateStory(@PathVariable Integer userId, @RequestBody Story story) throws UserException, StoryException {
	       
	            Story updatedStory = storyService.updateStory(userId, story);
	    		return new ResponseEntity<Story >(updatedStory,HttpStatus.OK);
	         
	    }

	    @DeleteMapping("/deleteStory/{userId}/{storyId}")
	    public ResponseEntity<String> deleteStory(@PathVariable Integer userId, @PathVariable Integer storyId) {
	        try {
	            String response = storyService.deleteStory(userId, storyId);
	            return ResponseEntity.ok(response);
	        } catch (StoryException e) {
	            return ResponseEntity.status(400).body(e.getMessage());
	        }
	    }
	
//	    @PostMapping("/saveStory/{userId}/{storyId}")
//	    public ResponseEntity<String> saveStory
//	    (@PathVariable Integer userId, @PathVariable Integer storyId) 
//	  	throws StoryException {
//	     
//	    		String savedStory = storyService.SavedStory(userId, storyId);
//	    		return new ResponseEntity<String >(savedStory,HttpStatus.OK);
//	    }
	    
	
	
	
}
