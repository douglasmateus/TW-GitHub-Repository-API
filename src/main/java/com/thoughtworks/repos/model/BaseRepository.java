package com.thoughtworks.repos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRepository {
	private long contributors;
	private long stars;
	private long forks;
	private String repositories;
	private List<Contributor> topContributors;
	
	public BaseRepository(long contributors, long stars, long forks, String repositories, List<Contributor> topContributors) {
		this.contributors = contributors;
		this.stars = stars;
		this.forks = forks;
		this.repositories = repositories;
		this.topContributors = topContributors;
	}
	public long getContributors() {
		return contributors;
	}
	public void setContributors(long contributors) {
		this.contributors = contributors;
	}
	public long getStars() {
		return stars;
	}
	public void setStars(long stars) {
		this.stars = stars;
	}
	public long getForks() {
		return forks;
	}
	public void setForks(long forks) {
		this.forks = forks;
	}
	public String getRepositories() {
		return repositories;
	}
	public void setRepositories(String repositories) {
		this.repositories = repositories;
	}
	public List<Contributor> getTopContributors() {
		return topContributors;
	}
	@JsonProperty("topContributors")
	public void setTopContributors(List<Contributor> topContributors) {
		this.topContributors = topContributors;
	}
}
