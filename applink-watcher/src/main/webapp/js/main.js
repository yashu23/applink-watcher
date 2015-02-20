var index = 1;

function AppURL(url,enabled,secured,userkey,uservalue,passkey,password){
	this.url = url;
	this.enabled = enabled;
	this.userkey = userkey;
	this.uservalue = uservalue;
	this.passkey = passkey;
	this.password = password;
}


function addAppURL(){
	index++;
	var html = "<tr id=" + index + ">";
	html += "<td><input type=\"text\" id=\"a\"" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=\"b\"" + index + "></td>";
	html += "<td><input type=\"checkbox\" value='true' id=\"c\"" + index + "></td>";
	html += "<td><input type=\"text\" id=\"d\"" + index + "></td>";
	html += "<td><input type=\"text\" id=\"e\"" + index + "></td>";
	html += "<td><input type=\"text\" id=\"f\"" + index + "></td>";
	html += "<td><input type=\"text\" id=\"g\"" + index + "></td>";
	html += "<td title=\"remove app url\" onclick=removeAppURL(" + index + ")>X</td>";
	html += "</tr>";
	$("#appURLGroup").append(html);
	index++;
}


function removeAppURL(idx){
	$("#" + idx).remove();
	index--;
}

function submitValue() {
	var str = '';
	for(var i=1;i<=index;i++){
		str += $("#a" + i).val() + "," + $("#b" + i).is(':checked') + "," + $("#c" + i).is(':checked') + "," + $("#d" + i).val() + "," + $("#e" + i).val() + "," + $("#f" + i).val() + "," + $("#g" + i).val();
		str += "#";
	}
	alert(index);
	var name = $("#appURLGroupName").val();
	var interval = $("#interval").val();
	alert(name + ',' + interval);
	alert(str);
	
	$
	.ajax({
		url : 'app',
		type : 'POST',
		data : {
			name : name,
			interval : interval,
			str : str,
			method : 'addgroup'
		}
	})
	.done(function(){
		
	});
}


