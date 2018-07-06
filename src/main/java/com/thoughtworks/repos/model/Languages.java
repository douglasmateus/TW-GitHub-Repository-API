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
