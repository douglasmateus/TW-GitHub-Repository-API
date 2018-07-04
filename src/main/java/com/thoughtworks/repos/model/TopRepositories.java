package com.thoughtworks.repos.model;

import java.util.List;

public class TopRepositories {
	private String language;
	private long position;
	private long contributors;
	private long stars;
	private long forks;
	private String repository;
	private List<Contributor> topContributors;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
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
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public List<Contributor> getTopContributors() {
		return topContributors;
	}
	public void setTopContributors(List<Contributor> topContributors) {
		this.topContributors = topContributors;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contributors ^ (contributors >>> 32));
		result = prime * result + (int) (forks ^ (forks >>> 32));
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + (int) (position ^ (position >>> 32));
		result = prime * result + ((repository == null) ? 0 : repository.hashCode());
		result = prime * result + (int) (stars ^ (stars >>> 32));
		result = prime * result + ((topContributors == null) ? 0 : topContributors.hashCode());
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
		TopRepositories other = (TopRepositories) obj;
		if (contributors != other.contributors)
			return false;
		if (forks != other.forks)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (position != other.position)
			return false;
		if (repository == null) {
			if (other.repository != null)
				return false;
		} else if (!repository.equals(other.repository))
			return false;
		if (stars != other.stars)
			return false;
		if (topContributors == null) {
			if (other.topContributors != null)
				return false;
		} else if (!topContributors.equals(other.topContributors))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TopRepositories [language=" + language + ", position=" + position + ", contributors=" + contributors
				+ ", stars=" + stars + ", forks=" + forks + ", repository=" + repository + ", topContributors="
				+ topContributors + "]";
	}
}
