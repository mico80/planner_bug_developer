package com.ebuero.model;

import java.util.ArrayList;
import java.util.List;

public class StoryDistribution {
	private List<Story> stories = new ArrayList<Story>();
	private int points = 0;

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addStory(Story story) {
		this.stories.add(story);
		this.points = this.points + story.getEstimatedPoints();
	}
}
