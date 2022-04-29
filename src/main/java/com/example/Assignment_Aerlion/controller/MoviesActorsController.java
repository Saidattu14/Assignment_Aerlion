package com.example.Assignment_Aerlion.controller;
import com.example.Assignment_Aerlion.Services.AuthService;
import com.example.Assignment_Aerlion.model.*;
import com.example.Assignment_Aerlion.repository.ActorsAppearancesRepository;
import com.example.Assignment_Aerlion.repository.ActorsRepository;
import com.example.Assignment_Aerlion.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MoviesActorsController {
	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private ActorsRepository actorsRepository;

	@Autowired
	private ActorsAppearancesRepository actorsAppearancesRepository;

	@Autowired
	AuthService authService;


	private Actors actors;
	private Movies movie;


	public MoviesActorsController(MoviesRepository mr,ActorsRepository ar, ActorsAppearancesRepository aar)
	{

		this.moviesRepository = mr;
		this.actorsRepository = ar;
		this.actorsAppearancesRepository = aar;

	}

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
			Actors actors = actorsRepository.findBynconst(id);
			if(actors == null)
			{
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			List<Movies> list = new ArrayList<Movies>();
			Arrays.stream(actors.getKnownfortitles())
					.forEach(x-> list.add(moviesRepository.findBytconst(x)));
			return new ResponseEntity<>(new ActorID(actors, (List<Movies>) list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
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
			int pageoffset = 0;
			for(int i = 0; i<page;i++)
			{
				pageoffset = page * i+1 * 1000;
			}
			List<Actors> m1 = new ArrayList<Actors>();
			if(name.length() == 0)
			{
				actorsRepository.findAllActors(pageoffset,page_size).forEach(m1::add);
			}
			else
			{
				actorsRepository.findActorInPage(pageoffset,page_size,name).forEach(m1::add);
			}
			return new ResponseEntity<>(m1,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/actors/{id}/appearances")
	public ResponseEntity<List<Appearance>> getActorAppearances
			(@PathVariable String id,final Authentication authentication,
			 @RequestParam(required = false,defaultValue = "0") int page,
	@RequestParam(required = false,defaultValue = "10") long page_size) {
		try {
			if(authService.LoginAuthentication(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
			}
			else if(authService.RateLimitingRequests(authentication) == false)
			{
				return new ResponseEntity<>(null,HttpStatus.TOO_MANY_REQUESTS);
			}
			actors = actorsRepository.findBynconst(id);
			List<ActorAppearance> list1 = new ArrayList<ActorAppearance>();
			List<Appearance> list2 = new ArrayList<>();
			Arrays.stream(actors.getKnownfortitles())
					.forEach(x-> list1.add(actorsAppearancesRepository
							.findactorappearnces(actors
									.getNconst(),x)));
			list1.forEach(x -> list2
					.add(new Appearance (x,moviesRepository
							.findBytconst(x
									.getTconst()))));

			return new ResponseEntity<>(list2, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
			List<Movies> m1 = new ArrayList<Movies>();
			int pageoffset = 0;
			for(int i = 0; i<page;i++)
			{
				pageoffset = page * i+1 * 1000;
			}
			if(name.length() == 0)
			{
				moviesRepository.findallmovies(pageoffset,page_size).forEach(m1::add);
			}
			else
			{
				moviesRepository.findMovieInPage(pageoffset,page_size,name).forEach(m1::add);
			}
			return new ResponseEntity<>(m1,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
			movie = moviesRepository.findBytconst(id);
			if(movie == null)
			{
				return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(movie,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
