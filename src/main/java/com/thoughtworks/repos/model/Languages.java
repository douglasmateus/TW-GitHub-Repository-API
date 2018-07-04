package com.thoughtworks.repos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Languages implements Comparable<Languages>{
	
	private String language;
	private long contributors;
	private long stars;
	private long forks;
	private String repositories;
	@JsonIgnore
	private List<Contributor> topContributors;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
		result = prime * result + ((repositories == null) ? 0 : repositories.hashCode());
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
		Languages other = (Languages) obj;
		if (contributors != other.contributors)
			return false;
		if (forks != other.forks)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (repositories == null) {
			if (other.repositories != null)
				return false;
		} else if (!repositories.equals(other.repositories))
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
		return "Languages [language=" + language + ", contributors=" + contributors + ", stars=" + stars + ", forks="
				+ forks + ", repositories=" + repositories + ", topContributors=" + topContributors + "]";
	}
	@Override
	public int compareTo(Languages o) {
		if (this.contributors > o.getContributors() && 
			new String(this.repositories).split(",").length > new String(o.repositories).split(",").length && 
			this.stars > o.getStars() && 
			this.forks > o.getForks()) {
			return -1;
		} else if (this.contributors < o.getContributors() && 
			new String(this.repositories).split(",").length > new String(o.repositories).split(",").length && 
			this.stars < o.getStars() && 
			this.forks < o.getForks()) {
			return 1;
		}
		return 0;
	}
}
