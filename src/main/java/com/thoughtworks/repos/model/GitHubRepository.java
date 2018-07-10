package com.thoughtworks.repos.model;

import java.util.List;

public class GitHubRepository {

	private long id;
	private String name;
	private String language;
	private long stargazers_count;
	private long forks_count;
	private List<Contributor> contributors;

	public GitHubRepository() {
		id=0;
	}
	public GitHubRepository(long id, String name, String language, long stargazers, long forks) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.stargazers_count = stargazers;
		this.forks_count = forks;
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

	public long getStargazers_count() {
		return stargazers_count;
	}

	public String getLanguage() {
		return language;
	}

	public long getForks_count() {
		return forks_count;
	}
	
	public List<Contributor> getContributors() {
		return contributors;
	}
	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}
}
