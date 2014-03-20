package com.ebuero.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ebuero.dao.DeveloperDao;
import com.ebuero.dao.StoryDao;
import com.ebuero.model.Developer;
import com.ebuero.model.Story;
import com.ebuero.model.StoryDistribution;

@Controller
@Transactional(readOnly=false)
public class StoryController {
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private DeveloperDao developerDao;
	
	@RequestMapping(value="/stories", method=RequestMethod.GET)
	public ModelAndView getStories(ModelMap map) {
		List<Story> stories = storyDao.getAll(false, false);
		List<Developer> developers = developerDao.getAll();
		map.addAttribute("stories", stories);
		map.addAttribute("developers", developers);
		map.addAttribute("newStory", new Story());
		return new ModelAndView("stories");
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView editStory(@RequestParam(value="id", required=true) int id, ModelMap map) {
		Story story = storyDao.getById(id);
		map.addAttribute("story", story);
		List<Developer> developers = developerDao.getAll();
		map.addAttribute("developers", developers);
		return new ModelAndView("edit");
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody ModelAndView updateStory(@ModelAttribute("story") Story story, BindingResult result, SessionStatus status)
	{
		storyDao.update(story);
		return new ModelAndView("redirect:/stories.do");
	}
	
	@RequestMapping(value="/saveStory", method=RequestMethod.POST)
	public @ResponseBody ModelAndView saveStory(@ModelAttribute("newStory") Story newStory, BindingResult result, SessionStatus status)
	{
		storyDao.save(newStory);
		return new ModelAndView("redirect:/stories.do");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/weeks", method=RequestMethod.GET)
	public ModelAndView getWeeklyPlan(ModelMap map) {
		List<StoryDistribution> weeks = new ArrayList<StoryDistribution>();
		List<Story> stories = storyDao.getAll(false, true);
		int numberOfDevelopers = developerDao.countDevelopers();
		int totalPoints = 0;
		if(numberOfDevelopers>0) {
			totalPoints = 10 * numberOfDevelopers;
			MultiValueMap storiesAndPoints = new MultiValueMap();
			for(Story story : stories) {
				int points = story.getEstimatedPoints();
				if(points>0 && points<=totalPoints)
					storiesAndPoints.put(points, story);
			}
			//In order to avoid concurrent modification of the map I need to clone the keySet
			Integer[] keys = (Integer[]) storiesAndPoints.keySet().toArray(new Integer[storiesAndPoints.keySet().size()]);
			Arrays.sort(keys); //sorting to reduce the randomness
			StoryDistribution week = new StoryDistribution();
			
			for(Integer key : keys){
				List<Story> theseStories = (List<Story>) storiesAndPoints.remove(key);
				for(Story story : theseStories) {
					if((week.getPoints()+key)<=totalPoints) {
						week.addStory(story);
					}
					else {
						weeks.add(week);
						week = new StoryDistribution();
						week.addStory(story);
					}
				}
			}
			if(week.getPoints()>0) weeks.add(week);
		}
		map.addAttribute("weeks", weeks);
		map.addAttribute("total", totalPoints);
		return new ModelAndView("weeks");
	}

}
