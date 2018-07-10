package com.thoughtworks.repos.model;

import java.util.List;

public class ThoughtworksRepositories {
	
	private List<Language> languages;
	
	private List<TopRepository> topRepositories;
	
	public List<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	public List<TopRepository> getTopRepositories() {
		return topRepositories;
	}
	public void setTopRepositories(List<TopRepository> topRepositories) {
		this.topRepositories = topRepositories;
	}
}
