<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello Page</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
/*
	$(document).ready(function() {
		$("#loginForm").on("submit", validateForm);
	});
	function validateForm() {
		$("#nameAndAgeReq").hide();
		$("#nameReq").hide();
		$("#ageReq").hide();
		$("#ageIllegal").hide();
		var name = $("#j_name").val();
		var age = $("#j_age").val();
		if (name.length==0 && age.length==0) {
			$("#nameAndAgeReq").show();
			return false;
		} else if (name.length==0) {
			$("#nameReq").show();
			return false;
		} else if (age.length==0) {
			$("#ageReq").show();
			return false;
		} else if (!($.isNumeric(age) && Math.floor(age)==age)) {
			$("#ageIllegal").show();
			return false;
		} 
		return true;
	}
	*/
</script>
<style>
	.alert {
		color: red;
		background: #fdf1e5;
		font-size: 10px;
		line-height: 16px;
		width: 200px;
		margin: 10;
		position: relative;
	}
	h2 {
		color: blue;
	}
	.round {
		border-radius: 10px;
	}
</style>
</head>
<body>
<h2>Sample04: JSP + Spring3 MVC ~ HibernateDaoSupport</h2>
<!-- Alerts for missing form info  --> 
<div class="alert" style="display: none;" id="nameAndAgeReq">
	<p>Name and Age are required</p>
</div>
<div class="alert" style="display:none;" id="nameReq">
	<p>Name is required</p>
</div>
<div class="alert" style="display:none;" id="ageReq">
	<p>Age is required</p>
</div>
<div class="alert" style="display:none;" id="ageIllegal">
	<p>Age is NOT an integer</p>
</div>
<!-- Form -->
<form action="hello.html" method="post" id="loginForm">
	<table>
		<tr>
			<td>Name: </td>
			<td><input type="text" name="name" id="j_name"/></td>
		</tr>
		<tr>
			<td>Symbol: </td>
			<td><input type="text" name="symbol" id="j_age"/></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="reset" value="Clear" class="round"/>
				<input type="submit" value="Submit" class="round"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>