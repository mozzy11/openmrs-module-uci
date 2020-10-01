package org.openmrs.module.ucionchology.utils;

import org.openmrs.module.ucionchology.models.Action;
import org.openmrs.module.ucionchology.models.DayDrugDosage;
import org.openmrs.module.ucionchology.models.PatientProtocol;
import org.openmrs.module.ucionchology.models.Phase;

import org.openmrs.module.ucionchology.models.StageDay;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Calendar;

public class PatientCalenderData {
	
	public static JSONArray generateCalenderData(PatientProtocol patientProtocal) throws ParseException {
		Integer totalDays = 0;
		List<StageDay> daysList = new ArrayList<StageDay>();
		JSONArray eventsArray = new JSONArray();
		
		if (patientProtocal.getProtocol3().isCyclic()) {
			for (int y = 1; y <= patientProtocal.getProtocol3().getNumberOfPhases(); y++) {
				for (Phase phase : patientProtocal.getProtocol3().getSortedPhases()) {
					totalDays += phase.getNumberOfDays();
					for (StageDay day : phase.getSortedDays()) {
						daysList.add(day);
					}
				}
			}
		} else {
			for (Phase phase : patientProtocal.getProtocol3().getSortedPhases()) {
				totalDays += phase.getNumberOfDays();
				for (StageDay day : phase.getSortedDays()) {
					daysList.add(day);
				}
			}
		}
		
		Date startDate = patientProtocal.getDateStarted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatedStartDate = sdf.format(startDate);
		
		StageDay[] daysArray = new StageDay[daysList.size()];
		daysArray = daysList.toArray(daysArray);
		
		if (totalDays > 0) {
			for (int x = 0; x < totalDays; x++) {
				StageDay day = daysArray[x];
				
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(formatedStartDate));
				c.add(Calendar.DAY_OF_MONTH, x);
				
				if (day.getDosage().size() > 0) {
					
					for (DayDrugDosage drug : day.getDosage()) {
						JSONObject event = new JSONObject();
						event.put("title", drug.getDrugName());
						event.put("start", sdf.format(c.getTime()));
						event.put("rendering", "background");
						eventsArray.put(event);
					}
				}
				
				if (day.getDayActions().size() > 0) {
					for (Action action : day.getDayActions()) {
						JSONObject event = new JSONObject();
						event.put("title", action.getDescription());
						event.put("start", sdf.format(c.getTime()));
						event.put("rendering", "background");
						event.put("color", "#ff9f89");
						eventsArray.put(event);
					}
				}
			}
			
		}
		return eventsArray;
	}
	
}