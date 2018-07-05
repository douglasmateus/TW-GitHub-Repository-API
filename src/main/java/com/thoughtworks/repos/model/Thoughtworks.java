package com.thoughtworks.repos.model;

import java.util.List;

public class Thoughtworks {
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languages == null) ? 0 : languages.hashCode());
		result = prime * result + ((topRepositories == null) ? 0 : topRepositories.hashCode());
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
		Thoughtworks other = (Thoughtworks) obj;
		if (languages == null) {
			if (other.languages != null)
				return false;
		} else if (!languages.equals(other.languages))
			return false;
		if (topRepositories == null) {
			if (other.topRepositories != null)
				return false;
		} else if (!topRepositories.equals(other.topRepositories))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Thoughtworks [languages=" + languages + ", TopRepositories=" + topRepositories + "]";
	}
}
