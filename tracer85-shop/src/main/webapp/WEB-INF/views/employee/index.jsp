<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Manage Employees</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/user.css"/>
    <script type="text/javascript">
        $(document).ready(function () {
            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();

            $('table .delete').on('click', function () {
                var id = $(this).parent().find('#id').val();
                $('#deleteEmployeeModal #id').val(id);
            });

            $('table .edit').on('click', function () {
                var id = $(this).parent().find('#id').val();
                $.ajax({
                    type:'GET',
                    url: '${pageContext.request.contextPath }/api/employee/find/' + id,
                    success: function (employee){
                        $('#editEmployeeModal #id').val(employee.id);
                        $('#editEmployeeModal #name').val(employee.name);
                        $('#editEmployeeModal #email').val(employee.email);
                        $('#editEmployeeModal #address').val(employee.address);
                        $('#editEmployeeModal #phone').val(employee.phone);
                    }

                });
            });

        });
    </script>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Employees</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i
                                class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
                        <a href="${pageContext.request.contextPath}/employee/export/excel" class="btn btn-secondary"><i class="material-icons">&#xE24D;</i> <span>Export to Excel</span></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>No. </th>
                    <th>Name <a href="${pageContext.request.contextPath}/page/${currentPage}?sortField=name&sortDir=${reverseSortDir}"><i class="fa fa-sort"></i></a></th>
                    <th>Email <a href="${pageContext.request.contextPath}/page/${currentPage}?sortField=email&sortDir=${reverseSortDir}"><i class="fa fa-sort"></i></a></th>
                    <th>Address <a href="${pageContext.request.contextPath}/page/${currentPage}?sortField=address&sortDir=${reverseSortDir}"><i class="fa fa-sort"></i></a></i></th>
                    <th>Phone</th>
                    <th style="text-align: center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees }" varStatus="i">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.address}</td>
                        <td>${employee.phone}</td>
                        <td>
                            <div class="row">
                                <div class="col-sm-4">
                                    <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                    <input type="hidden" value="${employee.id}" id="id">
                                </div>
                                <div class="col-md-2">
                                    <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons"
                                                                                                     data-toggle="tooltip"
                                                                                                     title="Edit">&#xE254;</i></a>
                                    <input type="hidden" value="${employee.id}" id="id">
                                </div>
                                <div class="col-md-2">
                                    <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons"
                                                                                                         data-toggle="tooltip"
                                                                                                         title="Delete">&#xE872;</i></a>
                                    <input type="hidden" value="${employee.id}" id="id">
                                </div>

                            </div>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="clearfix" c:if="${totalPages > 1}">
                <div class="hint-text">Showing <b>5</b> out of <b>${totalItems}</b> entries</div>
                <ul class="pagination" >

                    <c:if test="${currentPage > 1}">
                        <li class="page-item"><a href="${pageContext.request.contextPath}/page/${currentPage-1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a></li>
                    </c:if>

                    <c:forEach var="page" begin="1" end="${totalPages}" varStatus="i">
                        <li class="page-item active"><a href="${pageContext.request.contextPath}/page/${i.index}?sortField=${sortField}&sortDir=${sortDir}" class="page-link">${i.index}</a></li>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item"><a href="${pageContext.request.contextPath}/page/${currentPage+1}?sortField=${sortField}&sortDir=${sortDir}">Next</a></li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Add Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath }/employee/create">
                <div class="modal-header">
                    <h4 class="modal-title">Add Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" required="required" name="name">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" required="required" name="email">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required="required" name="address"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" required="required" name="phone">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                    <input type="hidden" id="id" name="id">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath }/employee/update">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" required="required" name="name" id="name">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" required="required" name="email" id="email">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required="required" name="address" id="address"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" required="required" name="phone" id="phone">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Save">
                    <input type="hidden" id="id" name="id">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath }/employee/delete">
                <div class="modal-header">
                    <h4 class="modal-title">Delete Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these Records?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" value="Delete">
                    <input type="hidden" name="id" id="id">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
