if(userrole_js=="audit")
document.write('<input type="hidden" id="new2" name="new2" class="groovybutton" value="New" title="" onMouseOver="goLite(this.form.name,this.name)"   onMouseOut="goDim(this.form.name,this.name)" onClick="enable();"/>&nbsp;&nbsp;&nbsp;	  <input type="hidden" id="save" name="save" class="groovybutton" value="Save" title="" onMouseOver="goLite(this.form.name,this.name)"   onMouseOut="goDim(this.form.name,this.name)" onclick="return OnButton1();"/><input type="hidden" id="update" name="update" class="groovybutton" value="Upda" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="return OnButton2();"/>      ');
else if(userrole_js!="audit")
	document.write('<input type="button" id="new2" name="new2" class="groovybutton" value="New" title="" onMouseOver="goLite(this.form.name,this.name)"   onMouseOut="goDim(this.form.name,this.name)" onClick="enable();"/>&nbsp;&nbsp;&nbsp;	  <input type="submit" id="save" name="save" class="groovybutton" value="Save" title="" onMouseOver="goLite(this.form.name,this.name)"   onMouseOut="goDim(this.form.name,this.name)" onclick="return OnButton1();"/><input type="submit" id="update" name="update" class="groovybutton" value="Upda" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="return OnButton2();"/>      ');
document.write('&nbsp;&nbsp;&nbsp;      <input type="reset" id="clear" name="clear" class="groovybutton" value="Clear" onclick="disable();"	 title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)"/>      &nbsp;&nbsp;&nbsp;      <input type="button" id="cancel" name="cancel" class="groovybutton" value="Cancel" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="window.location=\'chome.jsp\'"/>      &nbsp;&nbsp;&nbsp;  <input type="button" id="query" name="query" class="groovybutton" value="Query"  title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="queryfunction();"/>');
