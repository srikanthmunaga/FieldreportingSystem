var GREGORIAN = 0;
var JULIAN = 1;
var year = 0;
var month = 0;
var day = 0;
var julianday = 0.0;
var modifiedjulianday = 0.0;

function ipart(r) {
	return Math.round(r - 0.5);
}
function getJulianDay() {
	return this.julianday;
}
function getModifiedJulianDay() {
	return this.modifiedjulianday;
}

function dateFormat(f) {
	df = f;
}

function parseDate(dateval) {
	// split is a Javascript 1.2 function
	var dary = dateval.split("-");

	var y = fix2DigitDate(dary[2]);
	m = dary[1];
	d = dary[0];

	var calendar;
	if (y > 1582)
		calendar = GREGORIAN;
	else if (y < 1582)
		calendar = JULIAN;
	else if (m < 10 | (m == 10 && d < 15))
		calendar = JULIAN;
	else
		calendar = GREGORIAN;
	i = new CustomDate(y, m, d, calendar);
	return i;
}// dateval

function fix2DigitDate(dateval) {
	var date = dateval + "" ;// dateval must be a string
	if (date.length < 3) {
		date = 1900 + date * 1.0;
		date = date + "" ;// to string
	}
	return date;
}

function CustomDate(yr, mo, da, type) {
	year = yr * 1.0; // convert string to float

	month = mo * 1.0;
	day = da * 1.0;
	if (year == 1582 && month == 10 && day > 4 && day < 15) {
		alert("Invalid date: 15 Oct immediately followed 4 Oct in the year 1582");
		document.datainput.dateerr.value = "??";
		return;
	}
	if (year < 0)
		year = year + 1; // B.C. date correction
	var a = ipart((14 - month) / 12);
	var y = year + 4800 - a;
	var m = month + 12 * a - 3;
	if (type == GREGORIAN) {
		julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
				- ipart(y / 100) + ipart(y / 400) - 32045;
	}
	if (type == JULIAN) {
		julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
				- 32083;
	}
	modifiedjulianday = julianday - 2400000.5; // Zero at 17 Nov 1858 00:00:00
												// UTC
	this.getJulianDay = getJulianDay();
	this.getModifiedJulianDay = getModifiedJulianDay();
}

function DaysArray(n) {
	for ( var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i == 4 || i == 6 || i == 9 || i == 11) {
			this[i] = 30;
		}
		if (i == 2) {
			this[i] = 29;
		}
	}
	return this;
}
/*
 * function frsdate() {
 *  }
 */
/* new end */
function validate() {

	if (document.getElementsByName("huma_id")[0].value == "") {
		alert("Please enter the Emp Id");
		document.getElementsByName("huma_id")[0].focus();
		document.getElementsByName("huma_id")[0].style.background = "#fffacd";
		return false;
	}
	if (document.getElementsByName("FRS_date")[0].value == "") {
		alert("Please enter the Date");
		document.getElementsByName("FRS_date")[0].focus();
		document.getElementsByName("FRS_date")[0].style.background = "#fffacd";
		return false;
	}// alert("after date checking");
	// Common code for dates validation
	var date = new RegExp("[0-9]");
	var currentTime = new Date();
	var mm = currentTime.getMonth() + 1;
	var dd = currentTime.getDate();
	var yyyy = currentTime.getFullYear();
	var sysdate = dd + "-" + mm + "-" + yyyy;
	sysdate = sysdate.split("-");
	var sysdate = new Date(sysdate[2], sysdate[1] - 1, sysdate[0]); 
	// alert("sysdate="+sysdate);
	// FRS_date validation starts here
	if (!date.test(document.form1.FRS_date.value)) {
		alert("Please select the FRS date");
		document.form1.FRS_date.focus();
		return false;
	}// alert("hi1");
	if (document.form1.FRS_date.value.replace(/^\s+/, '').replace(/\s+$/, '') != "") {
		var vnvdate = (document.form1.FRS_date.value).split("-");
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		//var returnval = false;// validformat
		if (!validformat.test(document.form1.FRS_date.value)) {
			alert("Please enter the FRS date correct format");
			document.form1.FRS_date.focus();
			return false;
		}// if date format checking
		var dayfield = vnvdate[0];
		var monthfield = vnvdate[1];
		var yearfield = vnvdate[2];
		var dayobj = new Date(yearfield, monthfield - 1, dayfield);
		if ((dayobj.getMonth() + 1 != monthfield)
				|| (dayobj.getDate() != dayfield)
				|| (dayobj.getFullYear() != yearfield)) {
			alert("Invalid month or date found in FRS date");
			document.form1.FRS_date.focus();
			return false;
		}
		// Date comparision code starts here
		var FRS_date = (document.form1.FRS_date.value).split("-");
		var FRS_date = new Date(FRS_date[2], FRS_date[1] - 1, FRS_date[0]); // alert("hey
																			// to
																			// FRS_date
																			// date
																			// is
																			// ="+FRS_date);
		if (FRS_date > sysdate) {
			alert("FRS date should not be grater than the System date");
			document.form1.FRS_date.focus();
			return false;
		}
		// Date comparision code ends here
	}// if(FRS_date!="")
	// alert("hi2");
	// FRS date validation code ends here

	/*
	 * var dtStr = document.getElementsByName("FRS_date")[0].value; var
	 * daysInMonth = DaysArray(12) var dtCh = "/"; var minYear = 1900; var
	 * maxYear = 2100;
	 * 
	 * var pos1 = dtStr.indexOf(dtCh)
	 * 
	 * var pos2 = dtStr.indexOf(dtCh, pos1 + 1)
	 * 
	 * var strDay = dtStr.substring(0, pos1)
	 * 
	 * var strMonth = dtStr.substring(pos1 + 1, pos2) var strYear =
	 * dtStr.substring(pos2 + 1)
	 * 
	 * strYr = strYear
	 * 
	 * if (strDay.charAt(0) == "0" && strDay.length > 1) strDay =
	 * strDay.substring(1)
	 * 
	 * if (strMonth.charAt(0) == "0" && strMonth.length > 1) strMonth =
	 * strMonth.substring(1)
	 * 
	 * for ( var i = 1; i <= 3; i++) { if (strYr.charAt(0) == "0" &&
	 * strYr.length > 1) strYr = strYr.substring(1) }
	 * 
	 * month = parseInt(strMonth) day = parseInt(strDay) year = parseInt(strYr)
	 * 
	 * if (pos1 == -1 || pos2 == -1) { alert("The date format should be :
	 * (dd-mm-yyyy)");
	 * 
	 * document.getElementsByName("FRS_date")[0].value = "";
	 * document.getElementsByName("FRS_date")[0].focus();
	 * document.getElementsByName("FRS_date")[0].style.background = "#fffacd";
	 * return false; } if (strDay.length < 1 || day<1 || day>31 || (month == 2 &&
	 * day > daysInFebruary(year)) || day > daysInMonth[month]) { alert("Please
	 * enter a valid day"); document.getElementsByName("FRS_date")[0].value =
	 * ""; document.getElementsByName("FRS_date")[0].focus();
	 * document.getElementsByName("FRS_date")[0].style.background = "#fffacd";
	 * return false; } if (strMonth.length < 1 || month<1 || month>12) {
	 * alert("Please enter a valid month");
	 * document.getElementsByName("FRS_date")[0].value = "";
	 * document.getElementsByName("FRS_date")[0].focus();
	 * document.getElementsByName("FRS_date")[0].style.background = "#fffacd";
	 * return false; }
	 * 
	 * if (strYear.length != 4 || year == 0 || year<minYear || year>maxYear) {
	 * alert("Please enter a valid 4 digit year between " + minYear + " and " +
	 * maxYear); document.getElementsByName("FRS_date")[0].value = "";
	 * document.getElementsByName("FRS_date")[0].focus();
	 * document.getElementsByName("FRS_date")[0].style.background = "#fffacd";
	 * return false; }
	 * 
	 * new ends
	 */

	function IsValidDate(myDate) {
		var filter = /^([012]?\d|3[01])-([Jj][Aa][Nn]|[Ff][Ee][bB]|[Mm][Aa][Rr]|[Aa][Pp][Rr]|[Mm][Aa][Yy]|[Jj][Uu][Nn]|[Jj][u]l|[aA][Uu][gG]|[Ss][eE][pP]|[oO][Cc]|[Nn][oO][Vv]|[Dd][Ee][Cc])-(19|20)\d\d$/;
		return filter.test(myDate);
	}
	function test() {
		var txtTest = document.getElementById("FRS_date");
		var isValid = IsValidDate(txtTest.value);
		if (isValid) {
			alert("Correct format");
		} else {
			alert("Incorrect format");
		}
		return isValid;
	}

	if (document.getElementsByName("FRS_village_count")[0].value == "") {
		alert("Please enter the No. of villages visited");
		document.getElementsByName("FRS_village_count")[0].focus();
		document.getElementsByName("FRS_village_count")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_village_count")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_village_count")[0].value = "";
		document.getElementsByName("FRS_village_count")[0].focus();
		document.getElementsByName("FRS_village_count")[0].style.background = "#fffacd";
		return false;
	}

	if (document.getElementsByName("FRS_Cust_count")[0].value == "") {
		alert("Please enter the No. of customers meet for the recovery");
		document.getElementsByName("FRS_Cust_count")[0].focus();
		document.getElementsByName("FRS_Cust_count")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_Cust_count")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_Cust_count")[0].value = "";
		document.getElementsByName("FRS_Cust_count")[0].focus();
		document.getElementsByName("FRS_Cust_count")[0].style.background = "#fffacd";
		return false;
	}

	if (document.getElementsByName("FRS_total_amt")[0].value == "") {
		alert("Please enter the amount recovered");
		document.getElementsByName("FRS_total_amt")[0].focus();
		document.getElementsByName("FRS_total_amt")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_total_amt")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_total_amt")[0].value = "";
		document.getElementsByName("FRS_total_amt")[0].focus();
		document.getElementsByName("FRS_total_amt")[0].style.background = "#fffacd";
		return false;
	}

	if (document.getElementsByName("FRS_total_accounts")[0].value == "") {
		alert("Please enter the no. of accounts recovered");
		document.getElementsByName("FRS_total_accounts")[0].focus();
		document.getElementsByName("FRS_total_accounts")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_total_accounts")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_total_accounts")[0].value = "";
		document.getElementsByName("FRS_total_accounts")[0].focus();
		document.getElementsByName("FRS_total_accounts")[0].style.background = "#fffacd";
		return false;
	}

	if (document.getElementsByName("FRS_od_amt")[0].value == "") {
		alert("Please enter the OD amount recovered recovered");
		document.getElementsByName("FRS_od_amt")[0].focus();
		document.getElementsByName("FRS_od_amt")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_od_amt")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_od_amt")[0].value = "";
		document.getElementsByName("FRS_od_amt")[0].focus();
		document.getElementsByName("FRS_od_amt")[0].style.background = "#fffacd";
		return false;
	}

	if (document.getElementsByName("FRS_od_accounts")[0].value == "") {
		alert("Please enter the no. of OD accounts closed");
		document.getElementsByName("FRS_od_accounts")[0].focus();
		document.getElementsByName("FRS_od_accounts")[0].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("FRS_od_accounts")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_od_accounts")[0].value = "";
		document.getElementsByName("FRS_od_accounts")[0].focus();
		document.getElementsByName("FRS_od_accounts")[0].style.background = "#fffacd";
		return false;
	}
	// Newly Added By Rajesh
//	if (document.getElementsByName("FRS_SDRCUST_COUNT")[0].value == "") {
//		alert("Please enter the No. of SDR Customers");
//		document.getElementsByName("FRS_SDRCUST_COUNT")[0].focus();
//		document.getElementsByName("FRS_SDRCUST_COUNT")[0].style.background = "#fffacd";
//		return false;
//	}
	if (document.getElementsByName("FRS_SDRCUST_COUNT")[0].value != "") {
	if (isNaN(document.getElementsByName("FRS_SDRCUST_COUNT")[0].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("FRS_SDRCUST_COUNT")[0].value = "";
		document.getElementsByName("FRS_SDRCUST_COUNT")[0].focus();
		document.getElementsByName("FRS_SDRCUST_COUNT")[0].style.background = "#fffacd";
		return false;
	}
	}
	else 
		document.getElementsByName("FRS_SDRCUST_COUNT")[0].value = 0;
	// Completed

	/*
	 * if(document.getElementsByName("FRS_village_count")[0].value==0) {
	 * alert("If No. of villages Visited is zero then another fields must be
	 * zero"); return false; }
	 */
	// var
	// FRS_total_amt=parseInt(document.getElementsByName("FRS_total_amt")[0].value);
	// var
	// FRS_total_accounts=parseInt(document.getElementsByName("FRS_total_accounts")[0].value);
	var FRS_total_amt = parseInt(document.getElementsByName("FRS_total_amt")[0].value);
	var FRS_od_amt = parseInt(document.getElementsByName("FRS_od_amt")[0].value);
	var FRS_total_accounts = parseInt(document
			.getElementsByName("FRS_total_accounts")[0].value);
	var FRS_od_accounts = parseInt(document
			.getElementsByName("FRS_od_accounts")[0].value);

	if (FRS_total_amt == 0 && FRS_total_accounts != 0) {
		alert("Total Recovery amount should not be zero when No.of recovery Accounts are there, pls check..!");
		// document.getElementsByName("FRS_total_accounts")[0].value="";
		document.getElementsByName("FRS_total_accounts")[0].focus();
		document.getElementsByName("FRS_total_accounts")[0].style.background = "#fffacd";
		return false;
	}
	if (FRS_total_amt > 0 && FRS_total_accounts == 0) {
		alert("No.of recovery Accounts should not be zero when Total Recovery amount is there, pls check..!");
		// document.getElementsByName("FRS_total_accounts")[0].value="";
		document.getElementsByName("FRS_total_accounts")[0].focus();
		document.getElementsByName("FRS_total_accounts")[0].style.background = "#fffacd";
		return false;
	}
	if (FRS_od_amt == 0 && FRS_od_accounts != 0) {
		alert("OD Amount Collected should not be zero when No.of OD Accounts closed are there, pls check..!");
		// document.getElementsByName("FRS_total_accounts")[0].value="";
		document.getElementsByName("FRS_od_accounts")[0].focus();
		document.getElementsByName("FRS_od_accounts")[0].style.background = "#fffacd";
		return false;
	}
	// if(FRS_total_amt!=0 && FRS_od_amt!=0)
	if (FRS_od_amt > FRS_total_amt) {
		alert("OD Amount Collected should be less than the Total Recovery Amount Collected");
		// document.getElementsByName("FRS_od_amt")[0].value="";
		document.getElementsByName("FRS_od_amt")[0].focus();
		document.getElementsByName("FRS_od_amt")[0].style.background = "#fffacd";
		return false;
	}
	// if(FRS_total_accounts!=0 && FRS_od_accounts!=0)
	if (FRS_od_accounts > FRS_total_accounts) {
		alert("No of OD Accounts closed should be less than the No of recovery Accounts");
		// document.getElementsByName("FRS_od_accounts")[0].value="";
		document.getElementsByName("FRS_od_accounts")[0].focus();
		document.getElementsByName("FRS_od_accounts")[0].style.background = "#fffacd";
		return false;
	}
	// alert("just before TAC CUS verification");
	var FRS_Cust_count = parseInt(document.getElementsByName("FRS_Cust_count")[0].value);
	var FRS_village_count = parseInt(document
			.getElementsByName("FRS_village_count")[0].value);
	if (FRS_total_accounts > FRS_Cust_count) {
		alert("No of recovery Accounts should be less than the No. of customers approached");
		// document.getElementsByName("FRS_Cust_count")[0].value="";
		document.getElementsByName("FRS_total_accounts")[0].focus();
		document.getElementsByName("FRS_total_accounts")[0].style.background = "#fffacd";
		return false;
	}
	//alert("after all the validaions and going for the SDR validation");
	var FRS_SDRcust_count = parseInt(document.getElementsByName("FRS_SDRCUST_COUNT")[0].value);
	//alert("FRS_SDRcust_count="+FRS_SDRcust_count);
	if(FRS_SDRcust_count!=0)
	if (FRS_SDRcust_count > FRS_Cust_count) {
			alert("No Of SDR Customers Identified should not be greater than the No. of customers approached");
			// document.getElementsByName("FRS_Cust_count")[0].value="";
			document.getElementsByName("FRS_SDRCUST_COUNT")[0].focus();
			document.getElementsByName("FRS_SDRCUST_COUNT")[0].style.background = "#fffacd";
			return false;
		}
	
	if (FRS_village_count == 0
			& ((FRS_Cust_count + FRS_od_accounts + FRS_SDRcust_count + FRS_od_amt
					+ FRS_total_accounts + FRS_total_amt) != 0)) {
		alert("No of Villages Visited must be Non Zero when other values there, pls check..!");
		document.getElementsByName("FRS_village_count")[0].focus();
		return false;
	}

}// validate
