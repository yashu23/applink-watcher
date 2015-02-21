<html>
	<head>
		<title>App link watcher</title>
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/main.js" type="text/javascript"></script>
		<style>
			body {
				font-family: Verdana;
				font-size: 10px;
			}
			table, table input{
				font-family: Verdana;
				font-size: 10px;			
			}
			#hd {
				text-align: right;
				font-weight: bolder;
			}
		</style>
	</head>
	<body>
		<div id="hd">Hide</div>
		<fieldset id="frm">
		<legend>Add New Application Group Monitoring</legend>
		<p >
		<span>Application Group Name&nbsp;</span><span><input type="text" id="appURLGroupName"></span>
		<span>Monitoring Interval&nbsp;</span><span><input type="text" id="interval" size="2" maxlength="2 "></span>
		<button onclick="addAppURL()">Add more rows</button>
		</p>
		<form>
			<table id="appURLGroup">
				<tr>
					<td>Application URL</td>
					<td>Enabled</td>
					<td>Secured</td>
					<td>User key</td>
					<td>User name</td>
					<td>Password key</td>
					<td>Password</td>
				</tr>
				<tr>
					<td><input name="url" id="a1" size="50"></td>
					<td><input type="checkbox" id="b1"/></td>
					<td><input type="checkbox" id="c1"/></td>
					<td><input name="userKey" id="d1"></td>
					<td><input name="userName" id="e1"></td>
					<td><input name="passwordKey" id="f1"></td>
					<td><input name="password" id="g1" type="password"></td>
				</tr>
			</table>
			<div><input type="button" onclick="submitValue()" value="Submit"/></div>
		</form>
		</fieldset>
		
			<h3>Currently Monitored Groups</h3>
			<table width="100%">  
				<tr>
					<td>App Group Name</td>
					<td>URL</td>
					<td>Status</td>
					<td>Secured</td>
					<td>Runtime Status</td>
					<td>Last Check Time</td>	
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>			
			</table>
	</body>
</html>