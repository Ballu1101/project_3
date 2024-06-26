<%@page import="in.co.rays.project_3.controller.OrderCtl"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Order View</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/book_shelf.jpg');
	background-size: 100%;
	padding-top: 8%;
	background-attachment: fixed;
}
</style>

</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.ORDER_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.OrderDto"
				scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (dto.getId() != null && id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update
								Order</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add Order</h3>
							<%
								}
							%>
							<!--Body-->
							<div>
								<%-- <%
									List list = (List) request.getAttribute("roleList");
								%> --%>

								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getId()%>">
								<input type="hidden" name="createdBy"
									value="<%=dto.getCreatedBy()%>"> <input type="hidden"
									name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
									type="hidden" name="createdDatetime"
									value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
								<input type="hidden" name="modifiedDatetime"
									value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
							</div>


							<span class="pl-sm-5"><b>Name</b> <span
								style="color: red;">*</span></span></br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-user-circle grey-text"
												style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" class="form-control" name="name"
										placeholder="Name"
										value="<%=DataUtility.getStringData(dto.getName())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("name", request)%></font></br>

							<div class="md-form">

								<span class="pl-sm-5"><b>Address</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-alt grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" class="form-control" name="address"
											placeholder="address"
											value="<%=DataUtility.getStringData(dto.getAddress())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("address", request)%></font></br>

								<%-- 
								<%
								if (dto.getId() == null || id <= 0) {
								%>

                               <span class="pl-sm-5"><b>Password</b>
	<span style="color: red;">*</span></span> </br>
    <div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-key grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="password" class="form-control" name="password" placeholder="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
      </div>
    </div>
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("password", request)%></font></br>
	
	<span class="pl-sm-5"><b>Confirm Password</b>
	<span style="color: red;">*</span></span> </br>							
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-key grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="password" class="form-control" name="confirmPassword" placeholder="confirmPassword" value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
      </div>
    </div>
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></br>
							<%
									}
								%>
	<span class="pl-sm-5"><b>Email Id</b>
	<span style="color: red;">*</span></span> </br>							
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-envelope grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="text" class="form-control" id="defaultForm-email" name="emailId" placeholder="email Id" value="<%=DataUtility.getStringData(dto.getLogin())%>">
      </div>
    </div>
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("emailId", request)%></font></br>
								
	<span class="pl-sm-5"><b>Mobile No</b>
	<span style="color: red;">*</span></span> </br>
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-phone-square grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="text" class="form-control" id="defaultForm-email" maxlength="10" name="mobileNo"placeholder="mobile No" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
      </div>
    </div>							
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></br>
							
	<span class="pl-sm-5"><b>Role</b><span style="color:red;">*</span></span></br>
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-user grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <%=HTMLUtility.getList("role", String.valueOf(dto.getRoleId()), list)%>
      </div>
    </div>							
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("role", request)%></font></br>							
								
	<span class="pl-sm-5"><b>Gender</b><span style="color: red;">*</span></span> </br>
							 
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-venus-mars grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        
									<%
										HashMap map = new HashMap();
										map.put("Male", "Male");
										map.put("Female", "Female");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%>
									<%=htmlList%></div>
      
    </div>		
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("gender", request)%></font></br>
							
	<span class="pl-sm-5"><b>DOB</b>
	<span style="color: red;">*</span></span></br>
	<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="text" id="udate" name="dob" class="form-control" placeholder="Date Of Birth" readonly="readonly" value="<%=DataUtility.getDateString(dto.getDob()) %>">
      </div>
    </div>	
	<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("dob", request)%></font></br>
					<%
							if (dto.getId() != null && id >0) {
							%>

							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=UserCtl.OP_UPDATE%>"> <input type="submit"
									name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=UserCtl.OP_CANCEL%>">

							</div>
							<%
								} else {
							%>
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=UserCtl.OP_SAVE%>"> <input type="submit"
									name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=UserCtl.OP_RESET%>">
							</div>
							

						</div>
						<%
							}
						%>
					</div>
				</div> --%>
								<div class="text-center">
									<%-- 
								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%BankCtl.OP_SAVE%>">  --%>

									<input type="submit" name="operation"
										class="btn btn-warning btn-md" style="font-size: 17px"
										value="<%=OrderCtl.OP_SAVE%>">
								</div>
		</form>
		</main>
		<div class="col-md-4 mb-4"></div>

	</div>

</body>
<%@include file="FooterView.jsp"%>


</body>
</html>
