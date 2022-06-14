package com.example.Assignment_Arelion.Controller;
import com.example.Assignment_Arelion.Services.AuthService;
import com.example.Assignment_Arelion.Services.ActorsMoviesService;
import com.example.Assignment_Arelion.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MoviesActorsController {

	@Autowired
	AuthService authService;
	@Autowired
	ActorsMoviesService actorsMoviesService;

	public MoviesActorsController(AuthService authService, ActorsMoviesService actorsMoviesService) {
		this.authService = authService;
		this.actorsMoviesService = actorsMoviesService;
	}

	/**
	 * This is a get request end point /actors/{id}.
	 * It fetches the actor data for specific id.
	 * @return the ActorID object in Json Form
	 */
	@GetMapping("/actors/{id}")
	public ResponseEntity<ActorsResponse> getActorWithId(@PathVariable String id, final Authentication authentication) {
		try {
			if(!authService.loginAuthentication(authentication))
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			else if(!authService.rateLimitingRequests(authentication))
			{
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);

			}
			ActorsResponse allActorsResponse = this.actorsMoviesService.actorDataWithId(id);
			if(allActorsResponse == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return new ResponseEntity<>(allActorsResponse, HttpStatus.OK);
		} catch (Exception e) {
			log.error(String.valueOf(e));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	/**
	 * This is a get request end point /actors.
	 * It fetches the all actor data.
	 * @return the List of Actors object in Json Form
	 */
	@GetMapping("/actors")
	public ResponseEntity <List<ActorsResponse>> getAllActors(
			final Authentication authentication,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int page_size,
			@RequestParam(required = false,defaultValue = "") String name) {
		try {
			if(!authService.loginAuthentication(authentication))
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			else if(!authService.rateLimitingRequests(authentication))
			{
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
			}
			List<ActorsResponse> listActors = this.actorsMoviesService.allActors(page, page_size, name);
			if(listActors == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			}
			return new ResponseEntity<>(listActors,HttpStatus.OK);
		} catch (Exception e) {
			log.error(String.valueOf(e));
			return ResponseEntity.internalServerError().body(null);
		}
	}
	/**
	 * This is a get request end point /actors/{id}/appearances.
	 * It fetches the actor data for specific id along with appearances details.
	 * @return the List of Appearance objects in Json Form
	 */
	@GetMapping("/actors/{id}/appearances")
	public ResponseEntity<List<ActorsAppearancesResponse>> getActorAppearances
			(@PathVariable String id,final Authentication authentication,
			 @RequestParam(required = false,defaultValue = "0") int page,
	@RequestParam(required = false,defaultValue = "10") int page_size) {
		try {
			if(!authService.loginAuthentication(authentication))
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			else if(!authService.rateLimitingRequests(authentication))
			{
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
			}
			List<ActorsAppearancesResponse> listActorAppearances = this.actorsMoviesService.actorAppearances(id, page, page_size);
			if(listActorAppearances == null)
			{
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(listActorAppearances, HttpStatus.OK);
		} catch (Exception e) {
			log.error(String.valueOf(e));
			return ResponseEntity.internalServerError().body(null);
		}
	}
	/**
	 * This is a get request end point /movies.
	 * It fetches the all movie's data.
	 * @return the List of Movies object in Json Form
	 */
	@GetMapping("/movies")
	public ResponseEntity<List<MoviesResponse>> getMovies(
			final Authentication authentication,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int page_size,
			@RequestParam(required = false,defaultValue = "") String name) {
		try {
			if(!authService.loginAuthentication(authentication))
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			else if(!authService.rateLimitingRequests(authentication))
			{
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
			}
			List<MoviesResponse> listMovies = this.actorsMoviesService.allMovies(page, page_size, name);
			if(listMovies == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return new ResponseEntity<>(listMovies,HttpStatus.OK);
		} catch (Exception e) {
			log.error(String.valueOf(e));
			return ResponseEntity.internalServerError().body(null);
		}
	}
	/**
	 * This is a get request end point /movie/{id}.
	 * It fetches the movie data for specific id.
	 * @return the Movie object in Json Form
	 */
	@GetMapping("/movies/{id}")
	public ResponseEntity<MoviesResponse> getMovieWithId(
			@PathVariable String id,final Authentication authentication) {
		try {
			if(!authService.loginAuthentication(authentication))
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
			else if(!authService.rateLimitingRequests(authentication))
			{
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
			}
			MoviesResponse movie = this.actorsMoviesService.movieWithId(id);
			if(movie == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return new ResponseEntity<>(movie,HttpStatus.OK);
		} catch (Exception e) {
			log.error(String.valueOf(e));
			return ResponseEntity.internalServerError().body(null);
		}
	}
}
