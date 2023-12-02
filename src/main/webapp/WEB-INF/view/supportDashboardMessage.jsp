<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.prj.issuetracker.model.Tickets, java.util.List"%>
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


<style>
/* Style for the blurred background */
.blur {
	filter: blur(5px);
	transition: filter 0.5s ease;
}

/* Style for the popup */
.popup {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: white;
	padding: 20px;
	z-index: 9999;
	display: none;
}

.unblur {
	filter: none;
}
</style>

</head>

<body>

	<div id="main-content"
		class="container-fluid position-relative bg-white d-flex p-0 blur">
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
				<a href="/supportDashboard" class="navbar-brand mx-4 mb-3">
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
							String username = (String) session.getAttribute("username");
						%>
						<h6 class="mb-0"><%=username%></h6>
						<span>Support Team</span>
					</div>
				</div>
				<div class="navbar-nav w-100">
					<a href="/supportDashboard" class="nav-item nav-link active"><i
						class="fa fa-tachometer-alt me-2"></i>Pending Issues</a> <a
						href="/supportResolved" class="nav-item nav-link"><i
						class="fa fa-th me-2"></i>Resolved Issues</a>
				</div>
			</nav>
		</div>
		<!-- Sidebar End -->


		<!-- Content Start -->
		<div class="content">
			<!-- Navbar Start -->
			<nav
				class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
				<a href="/supportDashboard"
					class="navbar-brand d-flex d-lg-none me-4">
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
							<a href="#" class="dropdown-item">My Profile</a> <a href="#"
								class="dropdown-item">Log Out</a>
						</div>
					</div>
				</div>
			</nav>
			<!-- Navbar End -->


			<!-- Sale & Revenue Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<div class="col-sm-6 col-xl-4">
						<div
							class="bg-light rounded d-flex align-items-center justify-content-between p-4">
							<i class="fa fa-chart-line fa-3x text-primary"></i>
							<div class="ms-3">
								<p class="mb-2">Pending Issues</p>
								<%
									Integer pendingIssues = (Integer) session.getAttribute("pendingIssues");
								%>
								<h6 class="mb-0"><%=pendingIssues%></h6>
							</div>
						</div>
					</div>
					<!-- <div class="col-sm-6 col-xl-3">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <i class="fa fa-chart-bar fa-3x text-primary"></i>
                            <div class="ms-3">
                                <p class="mb-2">Approved Issues</p>
                                <h6 class="mb-0">0</h6>
                            </div>
                        </div>
                    </div> -->
					<div class="col-sm-6 col-xl-4">
						<div
							class="bg-light rounded d-flex align-items-center justify-content-between p-4">
							<i class="fa fa-chart-area fa-3x text-primary"></i>
							<div class="ms-3">
								<p class="mb-2">Resolved Issues</p>
								<%
									Integer resolvedIssues = (Integer) session.getAttribute("resolvedIssues");
								%>
								<h6 class="mb-0"><%=resolvedIssues%></h6>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-xl-4">
						<div
							class="bg-light rounded d-flex align-items-center justify-content-between p-4">
							<i class="fa fa-chart-pie fa-3x text-primary"></i>
							<div class="ms-3">
								<p class="mb-2">Total Issues</p>
								<%
									Integer totalIssues = (Integer) session.getAttribute("totalIssues");
								%>
								<h6 class="mb-0"><%=totalIssues%></h6>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Sale & Revenue End -->


			<!-- Recent Sales Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="bg-light text-center rounded p-4">
					<div
						class="d-flex align-items-center justify-content-between mb-4 position-relative">
						<h6
							class="mb-0 position-absolute top-0 start-50 translate-middle ">TICKETS</h6>
						<!--      center ticket -->

					</div>
					<div class="table-responsive">
						<div class="table-responsive"
							style="max-height: 250px; overflow-y: auto;">
							<table
								class="table text-start align-middle table-bordered table-hover mb-0 table-striped"
								style="position: relative;">
								<thead
									style="position: sticky; top: 0; background-color: #f8f9fa;">
									<tr class="text-dark">
										<th scope="col">TID</th>
										<th scope="col">EID</th>
										<th scope="col">Ticket Creation Time</th>
										<th scope="col">Status</th>
										<th scope="col">Action</th>
										<th scope="col">Message</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Tickets> itemList = (List<Tickets>) session.getAttribute("displayPendingIssues");
										for (Tickets item : itemList) {
									%>
									<tr>

										<td><%=item.getTicketId()%></td>
										<td><%=item.getEmpId()%></td>
										<td><%=item.getCreationTime()%></td>
										<td><%=item.getStatus()%></td>
										<td>
											<!-- <a id="approveButton" class="btn btn-sm btn-success" href="#" onclick="openPopup()">Approve</a> 
											<a id="rejectButton" class="btn btn-sm btn-danger" href="#" onclick="openPopup()">Reject</a> -->
											<%-- <form action="/getMessage" name="rowID" >
										</form>
										<input type="button" id = "<%= item.getTicketId()%>" name="accept" value="accept"> --%>
											<form action="/getMessage" method="post">
												<input type="hidden" name="buttonId"
													value="<%=item.getTicketId()%>"> <input
													type="submit" name="choice" value="accept"
													class="btn btn-sm btn-success"> <input
													type="submit" name="choice" value="reject"
													class="btn btn-sm btn-danger">
											</form>
										</td>
										<td>-</td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- Recent Sales End -->





			<!-- feedback Start -->

			<!-- feedback End -->
		</div>
		<!-- Content End -->



		<!-- Back to Top -->
		<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
			class="bi bi-arrow-up"></i></a>
	</div>
	<div style="display: block;" class="popup">
		<h3>Message</h3>
		<form method="post" action="/saveMessage">
			<!-- id="feedbackForm" -->
			<textarea id="feedbackMessage" name="feedbackMessage"
				placeholder="Your feedback here" rows="4" cols="50" required></textarea>
			<br> <br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
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
	<script>
		function openPopup(action) {
			document.getElementById('main-content').classList.add('blur');
			document.getElementById('feedbackPopup').style.display = 'block';
		}
	</script>
</body>

</html>