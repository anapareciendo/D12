/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import domain.Contest;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {
	
	@Autowired
	private ContestService contestService;

	// Constructors -----------------------------------------------------------

	public ContestController() {
		super();
	}

	// Actions ---------------------------------------------------------------		

	@RequestMapping("/available")
	public ModelAndView available() {
		ModelAndView result;
		
		Collection<Contest> contests = this.contestService.findAvailableContest();

		result = new ModelAndView("contest/available");
		result.addObject("contests", contests);
		result.addObject("current", Calendar.getInstance().getTimeInMillis()/86400000);
		result.addObject("requestURI", "contest/available.do");

		return result;
	}
	
//	@RequestMapping("/display")
//	public ModelAndView display(@RequestParam int projectId) {
//		ModelAndView result;
//		
//		Project project = this.projectService.findOne(projectId);
//		Long days = this.projectService.getDaysToGo(projectId);
//		Integer brackers = this.projectService.getBackers(projectId);
//		Crown crown = this.crownService.findByUserAccountId(LoginService.getPrincipal().getId());
//		
//		result = new ModelAndView("project/display");
//		result.addObject("project", project);
//		Double currentGoal =  this.projectService.getCurrentGoal(projectId);
//		if(currentGoal==null){
//			currentGoal=0.0;
//		}
//		result.addObject("currentGoal", currentGoal);
//		result.addObject("days", days);
//		result.addObject("brackers", brackers);
//		result.addObject("crown", crown);
//
//		return result;
//	}

}
