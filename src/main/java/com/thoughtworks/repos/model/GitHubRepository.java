package com.thoughtworks.repos.model;

import java.util.List;

public class GitHubRepository {

	private long id;
	
	private String name;
	
	private long stargazers;
	
	private String language;
	
	private long forks;
	
	private List<Contributor> contributors;

	public GitHubRepository() {
		id=0;
	}
	public GitHubRepository(long id, String name, String language, long stargazers, long forks) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.stargazers = stargazers;
		this.forks = forks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getStargazers() {
		return stargazers;
	}

	public String getLanguage() {
		return language;
	}

	public long getForks() {
		return forks;
	}
	
	public List<Contributor> getContributors() {
		return contributors;
	}
	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}
}
