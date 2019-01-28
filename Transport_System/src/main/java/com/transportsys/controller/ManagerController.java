package com.transportsys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;
import com.transportsys.service.ManagerServiceImpl;

@Controller
@RequestMapping("manager")
@SessionAttributes("mgrObj")
public class ManagerController {
	
	@Autowired
	ManagerServiceImpl mgrService;
	
	@RequestMapping(value = "/mgrLogin", method = RequestMethod.GET)
	private ModelAndView managerLogin(HttpServletRequest request,
			@ModelAttribute("mgrLoginDtl") ManagerDTL requestMgrDtl) {
		ModelAndView mv = new ModelAndView();
			mv.setViewName("ManagerLogin");
			return mv;
	}
	
	@RequestMapping(value = "/loginSuccessfull", method = RequestMethod.POST)
	private ModelAndView loginSuccessfull(HttpServletRequest request,@ModelAttribute("mgrLoginDtl") ManagerDTL requestMgrDtl) {
		System.out.println("inside loginsuccessfull method");
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("mgrObj"));
		ModelAndView mv = new ModelAndView();
		if(!(session.getAttribute("mgrObj") == null)) {
			System.out.println("inside session object");
			mv.setViewName("RouteDetails");
			return mv;	
		}
		else {
			
			ManagerDTL mgrDtl = mgrService.managerLogin(requestMgrDtl.getMGR_USER_ID(),
					requestMgrDtl.getMGR_PASSWORD());
			if (mgrDtl != null) {
				mv.setViewName("RouteDetails");
				mv.addObject("mgrObj", mgrDtl);
				return mv;
			}
		}
			return mv;
	}
	
	@RequestMapping("/routeDetails")
	private @ResponseBody Map< String, TruckDTL> routeDetails(@SessionAttribute("mgrObj") ManagerDTL mgrObjDtl) {
		
		int brnId = mgrObjDtl.getBranchdtl().getBRN_ID();
		Map< String, TruckDTL> truckStatusDtl =  mgrService.routeDetails(brnId);
		return truckStatusDtl;
	}
	
	@RequestMapping("/updateStatus")
	private @ResponseBody String statusUpdate(HttpServletRequest request , HttpServletResponse response) {
		int truckId = Integer.parseInt(request.getParameter("truckId"));
		if(mgrService.updateStatus(truckId , request.getParameter("changedSts"))) {
			return "Updated";
		}
		else {
			return "Failed";
		}
	}
	
	@RequestMapping(value = "/viewInstlTruck" , method = RequestMethod.GET)
	private ModelAndView viewInstlTruck(@RequestParam("strtDtFrm") String strtDtFrm,@RequestParam("strtDtTo") String strtDtTo)  {
		ModelAndView mv = new ModelAndView("RouteDetails");
		Date frmDt = null;
		Date toDt = null;
		try {
			frmDt = new SimpleDateFormat("dd/MM/yy").parse(strtDtFrm);
			toDt = new SimpleDateFormat("dd/MM/yy").parse(strtDtTo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TruckDTL> truckDtlList = mgrService.viewIstalTruck(frmDt, toDt);
		mv.addObject("truckDtlList",truckDtlList);
		return mv;
	}
	
	@RequestMapping(value = "checkServiceView")
	private ModelAndView checkServicView() {
		ModelAndView mv = new ModelAndView("CheckService");
		mv.addObject("truckDtl",new TruckDTL());
		return mv;
	}
	
	@RequestMapping(value = "checkService" , method = RequestMethod.GET)
	private ModelAndView checkService(@ModelAttribute("truckDtl") TruckDTL truckDtl) {
		ModelAndView mv = new ModelAndView();
		System.out.println(truckDtl);
		if (truckDtl.getTRC_ROUTE_FROM() == null) {
			mv.setViewName("RouteDetails");
			return mv;

		} else {
			mv.setViewName("CheckService");
			List<TruckDTL> truckList = mgrService.getTruckList(truckDtl.getTRC_ROUTE_FROM(),truckDtl.getTRC_ROUTE_TO());
			mv.addObject("truckDtlList", truckList);
			return mv;
		}

	}
	
	@GetMapping("/checkService/{pageId}")
	public ModelAndView paginationCheckService(@CookieValue(value = "truckRouteFrom",defaultValue = " ") String truckRouteFrom,
			@CookieValue(value = "truckRouteTo",defaultValue = " ") String truckRouteTo, @PathVariable int pageId,
			@ModelAttribute("truckDtl") TruckDTL truckDtl, HttpServletResponse response) {
		System.out.println(truckRouteFrom);
		System.out.println(truckRouteTo);
		int totalRecordsPerPage = 5;
		int totalNoOfRecords = 0;
		if (truckDtl.getTRC_ROUTE_FROM() != null) {
			Cookie rtFrom = new Cookie("truckRouteFrom", truckDtl.getTRC_ROUTE_FROM());
			Cookie rtTo = new Cookie("truckRouteTo", truckDtl.getTRC_ROUTE_TO());
			response.addCookie(rtFrom);
			response.addCookie(rtTo);
		} else {
			truckDtl = new TruckDTL();
			truckDtl.setTRC_ROUTE_FROM(truckRouteFrom);
			truckDtl.setTRC_ROUTE_TO(truckRouteTo);

		}
		totalNoOfRecords = mgrService.getTotalNoOfRecords(truckDtl);
		int totalNoOfPages = (int) Math.ceil(totalNoOfRecords/(double)totalRecordsPerPage);
		pageId = ((pageId - 1) * 5) + 1;
		
		List<TruckDTL> truckDtlList = mgrService.getTruckListByPage(pageId, totalRecordsPerPage, truckDtl);
		ModelAndView mv = new ModelAndView("CheckService");
		mv.addObject("truckDtl", truckDtl);
		mv.addObject("truckDtlList", truckDtlList);
		mv.addObject("totalNoOfPages", totalNoOfPages);
		return mv;
	}
}

