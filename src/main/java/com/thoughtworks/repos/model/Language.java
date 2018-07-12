package com.thoughtworks.repos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"language", "contributors", "stars", "forks", "repositories", "topContributors"})
public class Language extends BaseRepository implements Comparable<Language>{
	
	private String language;
	
	public Language(String language, long contributors, long stars, long forks, String repositories, List<Contributor> topContributors) {
		super(contributors, stars, forks, repositories, topContributors);
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public int compareTo(Language o) {
		if (this.getContributors() > o.getContributors() && 
			new String(this.getRepositories()).split(",").length > new String(o.getRepositories()).split(",").length && 
			this.getStars() > o.getStars() && 
			this.getForks() > o.getForks()) {
			return -1;
		} else if (this.getContributors() < o.getContributors() && 
			new String(this.getRepositories()).split(",").length > new String(o.getRepositories()).split(",").length && 
			this.getStars() < o.getStars() && 
			this.getForks() < o.getForks()) {
			return 1;
		}
		return 0;
	}
}

