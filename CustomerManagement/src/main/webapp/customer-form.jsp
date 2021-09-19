

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Customer Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/CustomerManagement/" class="navbar-brand"> Customer Management Application </a>
			</div>

			<ul class="navbar-nav" >
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Customers</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${customer != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${customer == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${customer != null}">
            			Edit Customer
            		</c:if>
						<c:if test="${customer == null}">
            			Add New Customer
            		</c:if>
					</h2>
				</caption>

				<c:if test="${customer != null}">
					<input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Customer Name</label> <input type="text"
						value="<c:out value='${customer.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Customer Email</label> <input type="text"
						value="<c:out value='${customer.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Customer Contact</label> <input type="text"
						value="<c:out value='${customer.contact}' />" class="form-control"
						name="contact">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Name of Product</label> <input type="text"
						value="<c:out value='${customer.product}' />" class="form-control"
						name="product">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Cost of Product</label> <input type="text"
						value="<c:out value='${customer.price}' />" class="form-control"
						name="price">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>