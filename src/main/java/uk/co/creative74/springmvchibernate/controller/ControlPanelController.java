package uk.co.creative74.springmvchibernate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlPanelController {

	static final Logger appLog = LoggerFactory.getLogger("application-log");

	@RequestMapping(value = { "/controlPanel" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {
		
		appLog.debug("GET /controlPanel");

		return "admin/controlPanel";
	}

}
