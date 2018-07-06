package com.thoughtworks.repos.model;

import java.util.List;

public class ThoughtworksRepositories {
	
	private List<Languages> languages;
	
	private List<TopRepositories> topRepositories;
	
	public List<Languages> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}
	public List<TopRepositories> getTopRepositories() {
		return topRepositories;
	}
	public void setTopRepositories(List<TopRepositories> topRepositories) {
		this.topRepositories = topRepositories;
	}
}
