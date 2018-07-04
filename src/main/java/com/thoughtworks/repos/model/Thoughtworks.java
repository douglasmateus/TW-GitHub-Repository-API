package com.thoughtworks.repos.model;

import java.util.List;

public class Thoughtworks {
	
	private List<Languages> languages;
	private List<TopRepositories> TopRepositories;
	
	public List<Languages> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}
	public List<TopRepositories> getTopRepositories() {
		return TopRepositories;
	}
	public void setTopRepositories(List<TopRepositories> TopRepositories) {
		this.TopRepositories = TopRepositories;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languages == null) ? 0 : languages.hashCode());
		result = prime * result + ((TopRepositories == null) ? 0 : TopRepositories.hashCode());
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
		if (TopRepositories == null) {
			if (other.TopRepositories != null)
				return false;
		} else if (!TopRepositories.equals(other.TopRepositories))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Thoughtworks [languages=" + languages + ", TopRepositories=" + TopRepositories + "]";
	}
}
