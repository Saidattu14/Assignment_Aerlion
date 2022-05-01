package com.example.Assignment_Arelion;

import com.example.Assignment_Arelion.Enums.AccountStatusinfo;
import com.example.Assignment_Arelion.Services.AuthService;
import com.example.Assignment_Arelion.model.UserData;
import com.example.Assignment_Arelion.repository.ActorsAppearancesRepository;
import com.example.Assignment_Arelion.repository.ActorsRepository;
import com.example.Assignment_Arelion.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssignmentArelionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentArelionApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private TestRestTemplate template;

	@Autowired
	private AuthService authService;

    @Autowired
	private ActorsRepository actorsRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private ActorsAppearancesRepository actorsAppearancesRepository;
	@Test
	public void TestingAuthRequestOnApiServiceForUnauthorizedUser_shouldSucceedWith401() throws Exception {
		ResponseEntity<Object> result = template.withBasicAuth("spring", "secret")
				.getForEntity("/api/v1/actors", Object.class);
		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	public void TestingAuthRequestOnApiServiceForAuthorizedUser_shouldSucceedWith200() throws Exception {
		authService.usersMap.put("sai",new UserData("sai","dattu",1,LocalTime.now(),AccountStatusinfo.Actived));
		ResponseEntity<Object> result = template.withBasicAuth("sai", "dattu")
				.getForEntity("/api/v1/actors", Object.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void TestingApiServiceForAuthorizedUserExceedsRequestLimit_shouldSucceedWith429() throws Exception {
		authService.usersMap.put("sai",new UserData("sai","dattu",1,LocalTime.now(),AccountStatusinfo.Actived));
		for(int i = 0; i<=4;i++)
		{
			ResponseEntity<Object> result = template.withBasicAuth("sai", "dattu")
					.getForEntity("/api/v1/actors", Object.class);
		}
		ResponseEntity<Object> result1 = template.withBasicAuth("sai", "dattu")
				.getForEntity("/api/v1/actors", Object.class);
		assertEquals(HttpStatus.TOO_MANY_REQUESTS, result1.getStatusCode());
	}

	@Test
	public void TestingAuthRequestRegisterUser_shouldSucceedWith201() throws Exception {
		ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
				.getForEntity("/register/user", String.class);
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	@Test
	public void TestingAuthRequestRegisterUserConfiltWithAnotherUser_shouldSucceedWith201() throws Exception {
		authService.usersMap.put("sajsji",new UserData("sajsji","dattsju",1,LocalTime.now(),AccountStatusinfo.Actived));
		ResponseEntity<String> result = template.withBasicAuth("sajsji", "dattsju")
				.getForEntity("/register/user", String.class);
		assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
	}
	@Test
	public void TestingAPIServiceWithFetchingActorWithValidID() throws Exception {
		try {

			actorsRepository.CreateActorTable();
			String [] knownForTitles = new String[] {"tt03","tt01", "tt02", "tt07"};
			String [] primaryProfession = new String[] {"actress","soundtrack", "music_department"};
			actorsRepository.InsertActorTable("nm04",
					"Brigitte",
					1943,
					0,
					primaryProfession
					,knownForTitles);
			ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
					.getForEntity("/api/v1/actors/nm04", String.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());
		}
		catch (Exception e)
		{
         System.out.println(e);
		}
	}
	@Test
	public void TestingAPIServiceWithFetchingActorWithInValidID() throws Exception {
		try {
			ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
					.getForEntity("/api/v1/actors/nm05", String.class);
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	@Test
	public void TestingAPIServiceWithFetchingMovieWithValidID() throws Exception {
		try {
			moviesRepository.CreateMovieTable();
			moviesRepository.InsertMovieTable("tt11",
					"movie",
					2012,
					"Grizzly II: Revenge",
					"Grizzly II: The Predator",
					"Horror,Music,Thriller",
					164,
					2010,
					 true
					);
			ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
					.getForEntity("/api/v1/movies/tt11", String.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	@Test
	public void TestingAPIServiceWithFetchingMovieWithInValidID() throws Exception {
		try {

			ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
					.getForEntity("/api/v1/movies/tt19", String.class);
			assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	@Test
	public void TestingAPIServiceWithFetchingActorWithValidIDAndAppearances() throws Exception {
		try {
			moviesRepository.CreateMovieTable();
			actorsRepository.CreateActorTable();
			actorsAppearancesRepository.CreateAppearanceTable();
			moviesRepository.InsertMovieTable("tt12",
					"movie",
					2012,
					"Grizzly II: Revenge",
					"Grizzly II: The Predator",
					"Horror,Music,Thriller",
					164,
					2010,
					true
			);
			String [] knownForTitles = new String[] {"tt12"};
			String [] primaryProfession = new String[] {"actress","soundtrack", "music_department"};
			actorsRepository.InsertActorTable("nm06","Brigitte",1943,0,primaryProfession,knownForTitles);

			ResponseEntity<String> result = template.withBasicAuth("sajssji", "dattssju")
					.getForEntity("/api/v1/actors/nm06/appearances", String.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}