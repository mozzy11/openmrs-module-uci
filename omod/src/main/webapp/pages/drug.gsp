<%
    ui.decorateWith("appui", "standardEmrPage")
%>

<script type="text/javascript">
var breadcrumbs = [
    { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' } ,
     { label: "${ ui.message("Drug Page")}" }
];
</script>

<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>

 ${ ui.includeFragment("ucionchology", "drugProtocalDetails") }

<form id="drugForm" method="post">

<fieldset id="create_drug" >
    <legend>
      <p>Add Drug Dose</p>
      </legend>
      ${ ui.includeFragment("ucionchology", "drugList") }
    
   <p >
   ${ ui.includeFragment("uicommons", "field/text", [ "id": "dosageValue", "label": "doze value", "formFieldName": "dosageValue" ])}  
  </p>

   <p >
   ${ ui.includeFragment("uicommons", "field/text", [ "id": "maxdosageValue", "label": "Maximum doze value", "formFieldName": "maxDoseValue" ])}  
  </p>
 
  <p >
   ${ ui.includeFragment("uicommons", "field/text", [ "id": "instructions", "label": "Instructions", "formFieldName": "instruction" ])}  
  </p>
    <p>
     <p>Select days</p>
     <%phaseDays.each {otherDay -> %> 
          <input type="checkbox" id="${otherDay.id}" name="days" value="${otherDay.id}">
          <label for="${otherDay.id}">${otherDay.dayNumber}</label><br>
        <% } %>
    </p>

<button id="submit" type="submit">Add Drug</button>
</fieldset>
</form>
