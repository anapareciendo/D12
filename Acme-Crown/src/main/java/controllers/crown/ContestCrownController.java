/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.crown;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.ProjectService;
import controllers.AbstractController;
import domain.Project;

@Controller
@RequestMapping("/contest/crown")
public class ContestCrownController extends AbstractController {
	
	@Autowired
	private ContestService contestService;
	@Autowired
	private ProjectService projectService;

	// Constructors -----------------------------------------------------------

	public ContestCrownController() {
		super();
	}

	// Actions ---------------------------------------------------------------		

//	@RequestMapping("/join")
//	public ModelAndView available(@RequestParam int contestId) {
//		ModelAndView result;
//		
//		Collection<Project> projects = this.projectService.findMyAvailableProjects();
//		
//		result = new ModelAndView("contest/join");
//		result.addObject("contests", contests);
//		result.addObject("current", Calendar.getInstance().getTimeInMillis()/86400000);
//		result.addObject("requestURI", "contest/available.do");
//
//		return result;
//	}
	
}
