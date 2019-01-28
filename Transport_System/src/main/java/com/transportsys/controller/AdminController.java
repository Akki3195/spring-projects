package com.transportsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.transportsys.model.AdminDTL;
import com.transportsys.model.BranchDTL;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;
import com.transportsys.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService as;
	/*
	 * @Autowired AdminService as;
	 */

	@RequestMapping(value = "/adLogin", method = RequestMethod.GET)
	private ModelAndView adminLogin() {
		return new ModelAndView("AdminLogin", "adminDetails", new AdminDTL());

	}

	@RequestMapping(value = "/ChkLogin", method = RequestMethod.POST)
	private String submit(@ModelAttribute("adminDetails") AdminDTL adDetail, ModelMap model) {
		if (as.checkUser(adDetail.getAD_USER_ID(), adDetail.getAD_PASSWORD())) {
			return "AdminWelcome";
		} else {
			return "AdminLogin";
		}
	}

	@RequestMapping(value = "/create_new")
	private ModelAndView createNew(HttpServletRequest request) {
		

		if (request.getParameter("new").equals("M")) {
			ModelAndView mv = new ModelAndView("CreateNewManager", "new_manager_form", new ManagerDTL());
			List<BranchDTL> brnNames = as.GetBranchDtl();
			mv.addObject("brnNames", brnNames);
			return mv;
		} else if (request.getParameter("new").equals("T")) {
			ModelAndView mv = new ModelAndView("InstallNewTruck", "new_truck_form", new TruckDTL());
			List<BranchDTL> brnNames = as.GetBranchDtl();
			mv.addObject("brnNames", brnNames);
			return mv;
		}
		return null;
	}

	@RequestMapping(value = "submit_form", method = RequestMethod.POST)
	private String saveDetails(@ModelAttribute("new_manager_form") ManagerDTL managerDtl,
			@ModelAttribute("new_truck_form") TruckDTL truckDtl, HttpServletRequest request, Model model) {
		if (request.getParameter("saveVar").equals("M")) {
			if (as.saveManagerDtl(managerDtl)) {
				model.addAttribute("value", "S");
				return "AdminWelcome";
			} else
				model.addAttribute("value", "F");
			return "AdminWelcome";
		}

		else if (request.getParameter("saveVar").equals("T")) {
			if (as.saveTruckDtl(truckDtl)) {
				model.addAttribute("value", "S");
				return "AdminWelcome";
			} else
				model.addAttribute("value", "F");
			return "AdminWelcome";
		}
		return "Invalid URL";

	}

	/*
	 * @RequestMapping(value = {"/findManager","/findTruck"},method =
	 * RequestMethod.GET)
	 */
	@RequestMapping(value = "/find/{var}", method = RequestMethod.GET)
	private ModelAndView find(@PathVariable("var") String s, HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		ModelAndView mv = null;
		List<BranchDTL> brnNames = as.GetBranchDtl();
		if (s.equals("M")) {
			mv = new ModelAndView("FindManger", "mgrDtl", new ManagerDTL());
			mv.addObject("brnNames", brnNames);
			return mv;
		} else if (s.equals("T")) {
			mv = new ModelAndView("FindTruck", "truckDtl", new TruckDTL());
			mv.addObject("brnNames", brnNames);
		}
		return mv;

		/*
		 * ModelAndView mv = null; mv = new ModelAndView("FindManger","mgrDtl", new
		 * ManagerDTL()); return mv;
		 */
	}

	@RequestMapping(value = "/find/searchByName", method = RequestMethod.GET)
	private @ResponseBody List<ManagerDTL> searchByName(HttpServletRequest request, HttpServletResponse response) {
		List<ManagerDTL> managerDtlList = null;
		if (request.getParameter("urlParam").isEmpty()) {
			managerDtlList = as.getManagerDtl(request.getParameter("manager_name_letter"),request.getParameter("urlParam"));
		} else {
			System.out.println(request.getParameter("manager_name_letter"));
			managerDtlList = as.getManagerDtl(request.getParameter("manager_name_letter"),request.getParameter("urlParam"));
		}
		return managerDtlList;
	}

	@RequestMapping(value = "/find/searchTruckByName", method = RequestMethod.POST)
	private @ResponseBody List<TruckDTL> searchTruck(@RequestParam(name = "letter") String letter,
			@RequestParam(name = "differ") String differ, HttpServletRequest request, HttpServletResponse response) {
		List<TruckDTL> truckDtl = null;
		truckDtl = as.getTruckDtl(letter, differ);
		return truckDtl;
	}

	@RequestMapping(value = "/find/deleteManager", method = RequestMethod.POST)
	private @ResponseBody String deleteManager(int mgrId) {
		if (as.deleteManager(mgrId)) {
			return "true";
		} else
			return "false";
	}
}
