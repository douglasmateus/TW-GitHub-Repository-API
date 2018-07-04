package com.thoughtworks.repos.model;

import java.util.List;

public class Repository {

	private long id;
	
	private String name;
	
	private long stargazers;
	
	private String language;
	
	private long forks;
	
	private List<Contributor> contributors;

	public Repository() {
		id=0;
	}
	public Repository(long id, String name, long stargazers, String language, long forks) {
		this.id = id;
		this.name = name;
		this.stargazers = stargazers;
		this.language = language;
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

	public void setName(String name) {
		this.name = name;
	}

	public long getStargazers() {
		return stargazers;
	}

	public void setStargazers(long stargazers) {
		this.stargazers = stargazers;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getForks() {
		return forks;
	}

	public void setForks(long forks) {
		this.forks = forks;
	}
	
	public List<Contributor> getContributors() {
		return contributors;
	}
	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (forks ^ (forks >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (stargazers ^ (stargazers >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repository other = (Repository) obj;
		if (forks != other.forks)
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stargazers != other.stargazers)
			return false;
		return true;
	}
}
