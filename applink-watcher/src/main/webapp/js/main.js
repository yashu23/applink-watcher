var index = 1;
var _debug = 0;

/* method for toggling display */
$(document).ready(function() {
	$("#hd").click(function() {
		if ($("#hd").html() == 'Show') {
			$("#frm").show();
			$("#hd").html('Hide');
		} else {
			$("#frm").hide();
			$("#hd").html('Show');
		}
	});
});

/* custom javascript app url object */
function AppURL(url, enabled, secured, userkey, uservalue, passkey, password) {
	this.url = url;
	this.enabled = enabled;
	this.secured = secured;
	this.userkey = userkey;
	this.uservalue = uservalue;
	this.passkey = passkey;
	this.password = password;
}

/* custom javascript app url group object */
function AppURLGroup(name, appurlarray, interval) {
	this.name = name;
	this.appurlarray = appurlarray;
	this.interval = interval;
}

/* adding new app url */
function addAppURL() {
	index++;
	var html = "<tr id=" + index + ">";
	html += "<td><input type=\"text\" size='50' id=a" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=b" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=c" + index + "></td>";
	html += "<td><input type=\"text\" id=d" + index + "></td>";
	html += "<td><input type=\"text\" id=e" + index + "></td>";
	html += "<td><input type=\"text\" id=f" + index + "></td>";
	html += "<td><input type=\"password\" id=g" + index + "></td>";
	html += "<td title=\"remove app url\" onclick=removeAppURL(" + index
			+ ")>X</td>";
	html += "</tr>";
	$("#appURLGroup").append(html);
	alert($("#" + index).html());
}

function removeAppURL(idx) {
	$("#" + idx).remove();
	index--;
}

function submitValue() {
	var appurlarray = [];
	for ( var i = 1; i <= index; i++) {
		var url = new AppURL($("#a" + i).val(), $("#b" + i).is(':checked'), $(
				"#c" + i).is(':checked'), $("#d" + i).val(), $("#e" + i).val(),
				$("#f" + i).val(), $("#g" + i).val());
		appurlarray.push(url);
		if(_debug)
			alert(JSON.stringify(url));
	}

	var name = $("#appURLGroupName").val();
	var interval = $("#interval").val();

	var appURLGroup = new AppURLGroup(name, appurlarray, interval);
	$.ajax({
		url : 'app',
		type : 'POST',
		dataType : 'json',
		data : {
			jsonvalue : JSON.stringify(appURLGroup),
			method : 'addgroup'
		}
	}).done(function(data) {
		var obj = JSON.parse(data);
		var groupdata = [];
		for(var i=0;i<obj.allGroup.length;i++){
			var appurlarray = [];
			for(var j=0;j<obj.allGroup[i].lstURL.length;j++) {
				AppURL url = AppURL(obj.allGroup[i].lstURL[j].url,obj.allGroup[i].lstURL[j].status,obj.allGroup[i].lstURL[j].bSecured,obj.allGroup[i].lstURL[j].userkey,obj.allGroup[i].lstURL[j].uservalue,obj.allGroup[i].lstURL[j].passkey,"");
				appurlarray.push(url);
			}
			AppURLGroup group = new AppURLGroup(obj.allGroup[i].name, appurlarray, obj.allGroup[i].interval);
			groupdata.push(group);
		}
		drawTable(groupdata);
	});
}

function drawTable(groupdata){
	$("#dvData").html('');
	$("#dvData").append("<h3>Currently Monitored Groups</h3>");
	var html = "<table><tr><td>App Group Name</td><td>URL</td><td>Status</td><td>Secured</td><td>Runtime Status</td><td>Last Check Time</td></tr>";
	for(var i=0;i<groupdata.length;i++) {
		html += "<tr>";
		for(var j=0;i<groupdata[i].appurlarray.length;j++) {
			html += "<td>" + groupdata[i].name + "</td>" 
			html += "<td>" + groupdata[i].appurlarray[j].url + "</td>";
			html += "<td>" + groupdata[i].appurlarray[j].enabled + "</td>";
			html += "<td>" + groupdata[i].appurlarray[j].secured + "</td>";
			html += "<td>" + groupdata[i].appurlarray[j].runtimestatus + "</td>";
			html += "<td>" + groupdata[i].appurlarray[j].lastcheckts + "</td>"; 
		}
		html += "</tr>";
	}
	html += "</table>";
	$("#dvData").append(html);
}
