package com.thoughtworks.repos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"language", "position", "contributors", "stars", "forks", "repositories", "topContributors"})
public class TopRepository extends Language {

	private long position;
	
	public TopRepository(String language, long contributors, long stars, long forks, String repositories, List<Contributor> topContributors, long position) {
		super(language, contributors, stars, forks, repositories, topContributors);
		this.position = position;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
}
