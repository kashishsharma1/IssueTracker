<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.prj.issuetracker.model.Tickets,com.prj.issuetracker.model.EmpInfo, java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>IssueTracker</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">
</head>

<script>
	function captureClickTime() {
		// Capture the current time when the button is clicked
		var currentTime = new Date().toISOString();

		// Parse the ISO timestamp into a JavaScript Date object
		var date = new Date(currentTime);

		// Format the Date as a SQL TIME string (HH:mm:ss)
		var sqlTime = date.toISOString().substr(11, 8);

		// Set the SQL TIME value in the hidden input field
		document.getElementById('clickTime').value = sqlTime;
	}
</script>
<style>
.edit-icon {
	position: relative;
	top: -33px; /* Adjust this value as needed */
	left: 800px; /* Adjust this value as needed */
}

.btn-bordered {
	border: 2px solid #9FC5E8; /* Set the border properties as desired */
	transition: all 0.3s ease;
}

.btn-bordered:hover {
	background-color: #9FC5E8; /* Set the background color on hover */
	color: white; /* Set the text color on hover if needed */
}

.btn-bordered:active {
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}

.blur {
	filter: blur(5px);
	-webkit-filter: blur(5px);
}
</style>

<body>
	
	<div class="container-fluid position-relative bg-white d-flex p-0">
		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- Spinner End -->


		<!-- Sidebar Start -->
		<div class="sidebar pe-4 pb-3">
			<nav class="navbar bg-light navbar-light">
				<a href="index.html" class="navbar-brand mx-4 mb-3">
					<h3 class="text-primary">
						<i class="fa fa-hashtag me-2"></i>IssueTracker
					</h3>
				</a>
				<div class="d-flex align-items-center ms-4 mb-4">
					<div class="position-relative">
						<img class="rounded-circle" src="img/user.jpg" alt=""
							style="width: 40px; height: 40px;">
						<div
							class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
					</div>
					<div class="ms-3">
						<%
						String username = (String) request.getAttribute("username");
						%>
						<h6 class="mb-0"><%=username%></h6>
						<span>Employee</span>
					</div>
				</div>
				<div class="navbar-nav w-100">
					<a href="/employee" class="nav-item nav-link "><i
						class="fa fa-tachometer-alt me-2"></i>New Issue</a> <a
						href="/employeePendingIssues" class="nav-item nav-link"><i
						class="fa fa-th me-2"></i>Pending Issues</a> <a
						href="/employeeResolvedIssues" class="nav-item nav-link"><i
						class="fa fa-table me-2"></i>Resolved Issues</a>

				</div>
			</nav>
		</div>
		<!-- Sidebar End -->


		<!-- Content Start -->
		<div class="content">
			<!-- Navbar Start -->
			<nav
				class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
				<a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
					<h2 class="text-primary mb-0">
						<i class="fa fa-hashtag"></i>
					</h2>
				</a> <a href="#" class="sidebar-toggler flex-shrink-0"> <i
					class="fa fa-bars"></i>
				</a>
				<div class="navbar-nav align-items-center ms-auto">

					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-bs-toggle="dropdown"> <img
							class="rounded-circle me-lg-2" src="img/user.jpg" alt=""
							style="width: 40px; height: 40px;"> <span
							class="d-none d-lg-inline-flex"><%=username%></span>
						</a>
						<div
							class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
							<a href="/profile" class="dropdown-item">My Profile</a> <a
								href="/logout" class="dropdown-item">Log Out</a>
						</div>
					</div>
				</div>
			</nav>
			<!-- Navbar End -->

			<div class="container-fluid pt-4 px-4">
				<div class="bg-light text-center rounded p-4">
					<div class="row">
						<div class="col-md-3 d-flex flex-column align-items-center">
							<img
								src="https://static-00.iconduck.com/assets.00/user-icon-2048x2048-ihoxz4vq.png"
								alt="User Icon" style="width: 200px; height: 200px;"
								class="mb-3">
							<%
							EmpInfo item = (EmpInfo) request.getAttribute("userDetails");
							%>
							<h1><%=item.getName()%></h1>
							<p>
								(<%=item.getRole().substring(0, 1).toUpperCase() + item.getRole().substring(1).toLowerCase()%>)
							</p>
							<form>
								<button type="button" class="btn btn-primary btn-bordered mt-2"
									onclick="openChangePasswordModal()">Change Password</button>
							</form>
							<div style="margin-left: -20px;">
								<c:if test="${not empty changepassword}">
									<p style="color: red">${changepassword}</p>
								</c:if>
							</div>
						</div>
						<div class="col-md-9">
							<form action="/updateProfile" method="post">
								<div class="form-group">
									<label for="userId" class="align-item-left">User ID:</label> <input type="text"
										class="form-control" id="userId" name="userId"
										placeholder=<%=item.getEmpId()%> disabled>
								</div>
								<div class="form-group">
									<label for="name">Name:</label> <input type="text"
										class="form-control" id="name" name="name"
										placeholder=<%=item.getName()%> disabled>

								</div>
								<div class="form-group">
									<label for="email">Email:</label> <input type="email"
										class="form-control" id="email" name="email"
										placeholder=<%=item.getEmail()%> disabled>

								</div>
								<div class="form-group">
									<label for="phone">Phone:</label> <input type="number"
										class="form-control" id="phone" name="phone"
										placeholder=<%=item.getPhone()%>> <span
										class="edit-icon" style="cursor: pointer;"><img
										src="https://cdn.pixabay.com/photo/2017/06/06/00/33/edit-icon-2375785_1280.png"
										alt="Edit Icon" style="width: 20px; height: 20px;"></span>
								</div>
								<div class="form-group">
									<label for="dob">Date of Birth:</label> <input type="text"
										class="form-control" id="dob" name="dob"
										placeholder=<%=item.getDob()%> disabled>

								</div>
								<div class="form-group">
									<label for="doj">Date of Joining:</label> <input type="text"
										class="form-control" id="doj" name="doj"
										placeholder=<%=item.getDoj()%> disabled>
								</div>
								<button type="submit" class="btn btn-primary  mt-3">Update
									Details</button>
							</form>
						</div>
					</div>
					<div class="modal fade" id="changePasswordModal" tabindex="-1"
						role="dialog" aria-labelledby="changePasswordModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="changePasswordModalLabel">Change
										Password</h5>
								</div>
								<div class="modal-body">
									<form action="/changePassword" method="post">
										<div class="form-group">
											<label for="oldPassword">Old Password:</label> <input
												type="password" class="form-control" id="oldPassword"
												name="oldPassword" placeholder="Old Password" required>
										</div>
										<div class="form-group">
											<label for="newPassword">New Password:</label> <input
												type="password" class="form-control" id="newPassword"
												name="newPassword" placeholder="New Password" required>
										</div>
										<div class="form-group">
											<label for="cnfPassword">Confirm Password:</label> <input
												type="password" class="form-control" id="cnfPassword"
												name="cnfPassword" placeholder="Confirm Password" required>
										</div>
										<br>
										<button type="submit" class="btn btn-primary">Update
											Password</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<!-- Content End -->


			<!-- Back to Top -->
			<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
				class="bi bi-arrow-up"></i></a>
		</div>

		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="lib/chart/chart.min.js"></script>
		<script src="lib/easing/easing.min.js"></script>
		<script src="lib/waypoints/waypoints.min.js"></script>
		<script src="lib/owlcarousel/owl.carousel.min.js"></script>
		<script src="lib/tempusdominus/js/moment.min.js"></script>
		<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
		<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

		<!-- Template Javascript -->
		<script src="js/main.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script>
			function openChangePasswordModal() {
				$('#changePasswordModal').modal('show');
				$('.container').addClass('blur');
			}

			$('#changePasswordModal').on('hidden.bs.modal', function(e) {
				$('.container').removeClass('blur');
			});
		</script>
</body>

</html>