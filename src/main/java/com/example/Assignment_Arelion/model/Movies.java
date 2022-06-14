package com.example.Assignment_Arelion.Model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movies {

	@Id
	private String tconst;
	private String titletype;
	private String primarytitle;
	private String originaltitle;
	private boolean isadult;
	private long startyear;
	private long endyear;
	private long runtimeminutes;
	private String genres;

	public Movies() {
	}



	public String getTconst() {
		return tconst;
	}


	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public String getTitleType() {
		return titletype;
	}

	public void setTitleType(String titletype) {
		this.titletype = titletype;
	}

	public String getPrimaryTitle() {
		return primarytitle;
	}

	public String getOriginalTitle() {
		return originaltitle;
	}

	public void setOriginalTitle(String originaltitle) {
		this.originaltitle = originaltitle;
	}

	public void setPrimaryTitle(String primarytitle) {
		this.primarytitle = primarytitle;
	}

	public boolean getIsAdult() {
		return isadult;
	}

	public void setIsAdult(boolean isadult) {

		this.isadult = isadult;
	}


	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}


	/**
	 * This is a class constructor where is assign movies data the class variables.
	 */
	public Movies(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, long startYear,long endYear, long runtimeMinutes, String genres) {
		this.tconst = tconst;
		this.titletype = titleType;
		this.endyear = endYear;
		this.primarytitle = primaryTitle;
		this.originaltitle = originalTitle;
		this.genres = genres;
		this.runtimeminutes = runtimeMinutes;
		this.startyear = startYear;
		this.isadult = isAdult;
	}

	public long getEndyear() {
		return endyear;
	}

	public long getRuntimeminutes() {
		return runtimeminutes;
	}

	public void setRuntimeminutes(long runtimeminutes) {
		this.runtimeminutes = runtimeminutes;
	}

	public void setEndyear(long endyear) {
		this.endyear = endyear;
	}


	public long getStartyear() {
		return startyear;
	}

	public void setStartyear(long startyear) {
		this.startyear = startyear;
	}


}
