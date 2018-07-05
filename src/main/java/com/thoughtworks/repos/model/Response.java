package com.thoughtworks.repos.model;

public class Response {

	private Thoughtworks thoughtworks;

	public Thoughtworks getThoughtworks() {
		return thoughtworks;
	}

	public void setThoughtworks(Thoughtworks thoughtworks) {
		this.thoughtworks = thoughtworks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thoughtworks == null) ? 0 : thoughtworks.hashCode());
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
		Response other = (Response) obj;
		if (thoughtworks == null) {
			if (other.thoughtworks != null)
				return false;
		} else if (!thoughtworks.equals(other.thoughtworks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Response [thoughtworks=" + thoughtworks + "]";
	}
}
