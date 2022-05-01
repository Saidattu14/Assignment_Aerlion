package com.example.Assignment_Arelion.controller;
import com.example.Assignment_Arelion.Services.AuthService;
import com.example.Assignment_Arelion.Services.ActorsMoviesService;
import com.example.Assignment_Arelion.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MoviesActorsController {

	@Autowired
	AuthService authService;
	@Autowired
	ActorsMoviesService actorsMoviesService;



	private ActorID actorID;
	private Movies movie;
	private List<Movies> ListMovies;
	private List<Appearance> ListActorAppearances;
	private List<Actors> ListActors;

	/**
	 * This is a get request end point /actors/{id}.
	 * It fetches the actor data for specific id.
	 * @return the ActorID object in Json Form
	 */
	@GetMapping("/actors/{id}")
	public ResponseEntity<ActorID> getActorWithId(@PathVariable String id, final Authentication authentication) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			actorID = actorsMoviesService.ActorDataWithId(id);
			if(actorID == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(actorID, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * This is a get request end point /actors.
	 * It fetches the all actor data.
	 * @return the List of Actors object in Json Form
	 */
	@GetMapping("/actors")
	public ResponseEntity <List<Actors>> getAllActors(
			final Authentication authentication,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int page_size,
			@RequestParam(required = false,defaultValue = "") String name) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			ListActors = actorsMoviesService.AllActors(page,page_size,name);
			if(ListActors == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(ListActors,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * This is a get request end point /actors/{id}/appearances.
	 * It fetches the actor data for specific id along with appearances details.
	 * @return the List of Appearance objects in Json Form
	 */
	@GetMapping("/actors/{id}/appearances")
	public ResponseEntity<List<Appearance>> getActorAppearances
			(@PathVariable String id,final Authentication authentication,
			 @RequestParam(required = false,defaultValue = "0") int page,
	@RequestParam(required = false,defaultValue = "10") int page_size) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			ListActorAppearances = actorsMoviesService.ActorAppearances(id,page,page_size);
			if(ListActorAppearances == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(ListActorAppearances, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * This is a get request end point /movies.
	 * It fetches the all movies data.
	 * @return the List of Movies object in Json Form
	 */
	@GetMapping("/movies")
	public ResponseEntity<List<Movies>> getMovies(
			String id,final Authentication authentication,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int page_size,
			@RequestParam(required = false,defaultValue = "") String name) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			ListMovies = actorsMoviesService.AllMovies(page,page_size,name);
			if(ListMovies == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(ListMovies,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * This is a get request end point /movie/{id}.
	 * It fetches the movie data for specific id.
	 * @return the Movie object in Json Form
	 */
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movies> getMovieWithId(
			@PathVariable String id,final Authentication authentication) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			movie = actorsMoviesService.MovieWithId(id);
			if(movie == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(movie,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
