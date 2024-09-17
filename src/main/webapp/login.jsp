
<%
if (session.getAttribute("email") != null) {
	response.sendRedirect("index");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	

	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-form">
						<h2 class="form-title">Sign In</h2>
						<form method="post" action="LoginService" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" id="email" name="email"
									placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>remember me</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
							
							<span>
								<a href="register" class="signup-image-link">I don't have an account</a>
							</span>
							<br>
							<span>
								<a href="restorepassword" class="signup-image-link">I forgot my password!</a>
							</span>
						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if(status == "failed")
			{
			swal("Wrong account or password", "Failed");
			}
		else if(status == "success")
			{
			swal("Account is registered", "Success");
			}
		else if(status == "unknow")
			{
			swal("Error","Error");
			}
	</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>