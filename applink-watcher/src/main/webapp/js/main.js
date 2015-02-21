var index = 1;

$(document).ready(function(){
	
	$("#hd").click(function(){
		
		if($("#hd").html() == 'Show') {
			$("#frm").show();
			$("#hd").html('Hide');
		}
		else {
			$("#frm").hide();
			$("#hd").html('Show');
		}
	});
	
});



function AppURL(url,enabled,secured,userkey,uservalue,passkey,password){
	this.url = url;
	this.enabled = enabled;
	this.secured = secured;
	this.userkey = userkey;
	this.uservalue = uservalue;
	this.passkey = passkey;
	this.password = password;
}


function AppURLGroup(name,appurlarray,interval) {
	this.name = name;
	this.appurlarray = appurlarray;
	this.interval = interval;
}

function addAppURL(){
	index++;
	var html = "<tr id=" + index + ">";
	html += "<td><input type=\"text\" size='50' id=a" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=b" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=c" + index + "></td>";
	html += "<td><input type=\"text\" id=d" + index + "></td>";
	html += "<td><input type=\"text\" id=e" + index + "></td>";
	html += "<td><input type=\"text\" id=f" + index + "></td>";
	html += "<td><input type=\"password\" id=g" + index + "></td>";
	html += "<td title=\"remove app url\" onclick=removeAppURL(" + index + ")>X</td>";
	html += "</tr>";
	$("#appURLGroup").append(html);
	alert($("#" + index).html());
}


function removeAppURL(idx){
	$("#" + idx).remove();
	index--;
}

function submitValue() {
	var appurlarray = [];
	for(var i=1;i<=index;i++){
		var url = new AppURL($("#a" + i).val(),$("#b" + i).is(':checked'),$("#c" + i).is(':checked'),$("#d" + i).val(),$("#e" + i).val(),$("#f" + i).val(),$("#g" + i).val());
		appurlarray.push(url);
		alert(JSON.stringify(url));
	}
	//alert(index);
	
	var name = $("#appURLGroupName").val();
	var interval = $("#interval").val();
	//alert(name + ',' + interval);

	
	var appURLGroup = new AppURLGroup(name,appurlarray,interval);
	//alert(JSON.stringify(appURLGroup));
	$
	.ajax({
		url : 'app',
		type : 'POST',
		dataType: 'json',
		data : { jsonvalue : JSON.stringify(appURLGroup), method: 'addgroup' }
	})
	.done(function(data){
		alert(data);
	});
}


