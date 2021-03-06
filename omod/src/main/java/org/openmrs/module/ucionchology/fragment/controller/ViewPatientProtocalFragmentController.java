package org.openmrs.module.ucionchology.fragment.controller;

import java.text.ParseException;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ucionchology.api.UCIOnchologyService;
import org.openmrs.module.ucionchology.models.PatientProtocol;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ViewPatientProtocalFragmentController {
	
	UCIOnchologyService onchlogyService;
	
	public void controller(PageModel model, @RequestParam("patientId") Patient patient) throws ParseException {
		
		onchlogyService = Context.getService(UCIOnchologyService.class);
		PatientProtocol patientProtocal = onchlogyService.getPatientProtocalByPatient(patient.getPatientId());
		model.addAttribute("innitialisedProtocal", patientProtocal);
	}
}
