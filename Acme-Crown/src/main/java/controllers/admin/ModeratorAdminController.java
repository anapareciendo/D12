/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CrownService;
import services.ModeratorService;
import controllers.AbstractController;
import domain.Crown;
import domain.Moderator;

@Controller
@RequestMapping("/moderator/admin")
public class ModeratorAdminController extends AbstractController {
	
	@Autowired
	private ModeratorService moderatorService;
	@Autowired
	private CrownService crownService;
	
	// Constructors -----------------------------------------------------------

	public ModeratorAdminController() {
		super();
	}

	// Actions ---------------------------------------------------------------	
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		Collection<Moderator> moderators = this.moderatorService.findAll();
		
		result = new ModelAndView("moderator/list");
		result.addObject("moderators", moderators);
		result.addObject("requestURI", "/moderator/admin/list.do");

		return result;
	}
	
	@RequestMapping(value="/ban",method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int moderatorId) {
		ModelAndView result;
		
		Collection<Moderator> moderators = this.moderatorService.findAll();
		
		result = new ModelAndView("moderator/list");
		result.addObject("moderators", moderators);
		result.addObject("requestURI", "/moderator/admin/list.do");
		
		try{
			this.moderatorService.ban(moderatorId);
		}catch(Throwable oops){
			result.addObject("message", "crow.commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value="/unban",method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam int moderatorId) {
		ModelAndView result;
		
		Collection<Moderator> moderators = this.moderatorService.findAll();
		
		result = new ModelAndView("moderator/list");
		result.addObject("moderators", moderators);
		result.addObject("requestURI", "/moderator/admin/list.do");
		
		try{
			this.moderatorService.unban(moderatorId);
		}catch(Throwable oops){
			result.addObject("message", "crow.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value="/level",method = RequestMethod.GET)
	public ModelAndView level(@RequestParam int moderatorId) {
		ModelAndView result;
		
		try{
			this.moderatorService.level(moderatorId);
			Collection<Moderator> moderators = this.moderatorService.findAll();
			
			result = new ModelAndView("moderator/list");
			result.addObject("moderators", moderators);
			result.addObject("requestURI", "/moderator/admin/list.do");
		}catch(Throwable oops){
			Collection<Moderator> moderators = this.moderatorService.findAll();
			
			result = new ModelAndView("moderator/list");
			result.addObject("moderators", moderators);
			result.addObject("requestURI", "/moderator/admin/list.do");
			result.addObject("message", "crow.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value="/ascend",method = RequestMethod.GET)
	public ModelAndView ascend() {
		ModelAndView result;
		
		Collection<Crown> crowns = this.crownService.findAllNotBanned();
		
		result = new ModelAndView("crown/ascend");
		result.addObject("crowns", crowns);
		result.addObject("requestURI", "/moderator/admin/ascend.do");

		return result;
	}
	
	@RequestMapping(value="/toMod",method = RequestMethod.GET)
	public ModelAndView toMod(@RequestParam int crownId) {
		ModelAndView result;
		
		
		try{
			this.moderatorService.crownToMod(crownId);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			Collection<Crown> crowns = this.crownService.findAllNotBanned();
			
			result = new ModelAndView("crown/ascend");
			result.addObject("crowns", crowns);
			result.addObject("requestURI", "/moderator/admin/ascend.do");
			result.addObject("message", "crown.commit.error");
		}

		return result;
	}
	
}
