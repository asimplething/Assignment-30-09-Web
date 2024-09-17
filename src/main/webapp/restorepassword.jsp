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
						<h2 class="form-title">Restore Password</h2>
						<form method="post" action="ForgotPassService" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" id="email" name="email" placeholder="Your Email" />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="find" id="find" class="form-submit"
									value="Find My Password" />
							</div>

							<span> <a href="login" class="signup-image-link">Try
									Login Again</a>
							</span>

						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	<input type="hidden" id="password"
		value="<%=request.getAttribute("password")%>">
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status == "failed") {
			swal("No email existing", "Failed");
		} else if (status == "success") {
			var password = document.getElementById("password").value;
			swal("Your Password is: " + password, "Success");
		} else if (status == "unknow") {
			swal("Error", "Error");
		}
	</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>