<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Start your development with a Dashboard for Bootstrap 4.">
  <meta name="author" content="Creative Tim">
  <meta name="referrer" content="strict-origin-when-cross-origin" />
  <base href="${pageContext.servletContext.contextPath}/">
  <title>Quản lý nhân viên</title>
  
  <%-- <link href="<c:url value='/resources/assets/dist/img/brand/favicon.png'/>" rel="image/png"> --%>
  <!-- Favicon -->
  <link rel="icon" href="<c:url value='resources/assets/dist/img/brand/favicon.png'/>" type="image/png">
  <!-- Fonts -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
  <!-- Icons -->
  <link rel="stylesheet" href="<c:url value='resources/assets/dist/vendor/nucleo/css/nucleo.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='resources/assets/dist/vendor/@fortawesome/fontawesome-free/css/all.min.css'/>" type="text/css">
  <!-- Page plugins -->
  <!-- Argon CSS -->
  <link rel="stylesheet" href="<c:url value='resources/assets/dist/css/argon.css?v=1.2.0'/>" type="text/css">
</head>

<body>
  <!-- Sidenav -->
  <nav class="sidenav navbar navbar-vertical  fixed-left  navbar-expand-xs navbar-light bg-white" id="sidenav-main">
    <div class="scrollbar-inner">
      <!-- Brand -->
      <div class="sidenav-header  align-items-center">
        <a class="navbar-brand" href="javascript:void(0)">
          <img src="resources/assets/dist/img/brand/blue.png" class="navbar-brand-img" alt="...">
        </a>
      </div>
      <div class="navbar-inner">
        <!-- Collapse -->
        <div class="collapse navbar-collapse" id="sidenav-collapse-main">
          <!-- Nav items -->
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="QLNV.htm">
                <i class="ni ni-circle-08 text-primary"></i>
                <span class="nav-link-text">Quản lý nhân viên</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="QLLH.htm">
                <i class="ni ni-badge text-green"></i>
                <span class="nav-link-text">Quản lý lớp học</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="QLSV.htm">
                <i class="ni ni-hat-3 text-orange"></i>
                <span class="nav-link-text">Quản lý sinh viên</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" onclick=" return theFunction2();">
                <i class="ni ni-single-02 text-yellow"></i>
                <span class="nav-link-text">User Info</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="changepass.htm">
                <i class="ni ni-key-25 text-black" ></i>
                <span class="nav-link-text">Change password</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout.htm">
                <i class="ni ni-user-run text-info"></i>
                <span class="nav-link-text">Logout</span>
              </a>
            </li>
            
          </ul>
          
        </div>
      </div>
    </div>
  </nav>
  <!-- Main content -->
  <div class="main-content" id="panel">
    <!-- Topnav -->
    
    <!-- Header -->
    <!-- Header -->
    <div class="header bg-primary pb-6">
      <div class="container-fluid">
        <div class="header-body">
          <div class="row align-items-center py-4">
          
            <div class="col-lg-6 col-7">
              <h6 class="h2 text-white d-inline-block mb-0">Welcome</h6>
              <nav aria-label="breadcrumb" class="d-none d-md-inline-block ml-md-4">
                <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                  <li class="breadcrumb-item"><a href="QLNV.htm"><i class="fas fa-home"></i></a></li>
                  <li class="breadcrumb-item"><a href="QLLH.htm">Quản Lý Lớp Học</a></li>
                  
                </ol>
              </nav>
            </div>
            
            
          </div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--6">
      <div class="row">
        <div class="col-xl-8 order-xl-2">
          <div class="row">
        <div class="col">
          <div class="card">
            <!-- Card header -->
            <div class="card-header border-0">
              <h3 class="mb-0">Table LOP</h3>
              
            </div>
            <!-- Light table -->
            <div class="table-responsive">
              <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                  	<th scope="col" class="sort" data-sort="name">STT</th>
                    <th scope="col" class="sort" data-sort="name">Mã Lớp</th>
                    <th scope="col" class="sort" data-sort="budget">Tên Lớp</th>
                    <th scope="col" class="sort" data-sort="status">Mã Nhân Viên</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody class="list">
                  <c:forEach var="x" items="${classes}" varStatus="i">
	                  <tr>
	                  	<th scope="row">
	                      <div class="media align-items-center">
	                        <div class="media-body">
	                          <span class="name mb-0 text-sm"> <c:out value="${i.index+1}"/> </span>
	                        </div>
	                      </div>
	                    </th>
	                  
	                    <th scope="row">
	                      <div class="media align-items-center">
	                        <div class="media-body">
	                          <span class="name mb-0 text-sm"> <c:out value="${x.malop}"/> </span>
	                        </div>
	                      </div>
	                    </th>
	                    
	                    <td>
	                      <span class="badge badge-dot mr-4">
	                        <span class="status"> <c:out value="${x.tenlop}"/> </span>
	                      </span>
	                    </td>
	                    
	                    <td>
	                      <div class="d-flex align-items-center">
	                        <span class="completion mr-2"> <c:out value="${x.manv}"/> </span>
	                        
	                      </div>
	                    </td>
	                    
	                    <td class="text-right">
	                      <div class="dropdown">
	                        <a class="btn btn-sm btn-icon-only text-light" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                          <i class="fas fa-ellipsis-v"></i>
	                        </a>
	                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
	                          <a class="dropdown-item" href="QLLH.htm?cn=sua&lp=${x}">Edit</a>
	                          <a class="dropdown-item" onclick=" return theFunction(h = 'xoaLop.htm?ml=${x.malop}');">Delete</a>
	                          
	                        </div>
	                      </div>
	                    </td>
	                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            
            
          </div>
          
          
          
        </div>
        
        
      </div>
        </div>
        <div class="col-xl-4 order-xl-1">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">Edit Class: <c:out value="${manvsua}"/> </h3>
                </div>
                <div class="col-4 text-right">
                  <!-- <a href="#!" class="btn btn-sm btn-primary">Settings</a> -->
                  <div class="col-md-4">
				      <button hidden="" id="ooo" type="button" class="btn btn-block btn-warning mb-3" data-toggle="modal" data-target="#modal-notification">Notification</button>
				      <div class="modal fade" id="modal-notification" tabindex="-1" role="dialog" aria-labelledby="modal-notification" aria-hidden="true">
					    <div class="modal-dialog modal-danger modal-dialog-centered modal-" role="document">
					        <div class="modal-content bg-gradient-danger">
					        	
					            <div class="modal-header">
					                <h6 class="modal-title" id="modal-title-notification">Your attention is required</h6>
					                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					                    <span aria-hidden="true">×</span>
					                </button>
					            </div>
					            
					            <div class="modal-body">
					            	
					                <div class="py-3 text-center">
					                    <i class="ni ni-bell-55 ni-3x"></i>
					                    <h4 class="heading mt-4">You should read this!</h4>
					                    <p> <c:out value="${message}"/> </p>
					                </div>
					                
					            </div>
					            
					            <div class="modal-footer">
					                
					                <button type="button" class="btn btn-link text-white ml-auto" data-dismiss="modal">Ok, Got it</button>
					            </div>
					            
					        </div>
					    </div>
					</div>
    
    
  </div>
                </div>
              </div>
            </div>
            <div class="card-body">
              <form:form action="themSuaLop${kt}${MALOP}.htm" method="post" modelAttribute="lop" autocomplete="off">
                <h6 class="heading-small text-muted mb-4">Class informationss</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-11">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">Mã lớp</label>
                        <form:input autocomplete="off" type="text" id="input-username" class="form-control" placeholder="MALOP" required="required" pattern="LOP[0-9]{2}" title="LOP?? example LOP01, LOP02" maxlength="20" path="malop" value="${lop2.malop}"/>
                      </div>
                    </div>
                    
                  </div>
                  <div class="row">
                    <div class="col-lg-11">
                      <div class="form-group">
                        <label class="form-control-label" for="input-first-name">Tên lớp</label>
                        <form:input autocomplete="off" type="text" id="input-first-name" class="form-control" placeholder="TENLOP" required="required" pattern="[A-za-z\s]{1,99}" maxlength="99" title=" no special characters, numbers" path="tenlop" value="${lop2.tenlop}"/>
                      </div>
                    </div>
                    
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-11">
                      <div class="form-group">
                        <label class="form-control-label" for="input-first-name">Mã nhân viên</label>
                        
                        <form:select autocomplete="off" class="form-control" required="required" path="manv">
  							<c:forEach var="x1" items="${ids}"> 
  								<option <c:if test="${lop2.manv == x1}">selected</c:if> value="${x1}">  ${x1}</option>
  								
  							</c:forEach>
  							
						</form:select>
                      </div>
                    </div>
                    
                  </div>
                  
                  <hr class="my-4" />
                  
                  <div class="row">
                    <div class="col-lg-11">
                      <div class="form-group">
                        <label class="form-control-label" for="input-first-name">Chức năng:  </label>
                        <div class="custom-control custom-radio custom-control-inline">
						  <input autocomplete="off" type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input" value="them" ${insertChecked}>
						  <label class="custom-control-label" for="customRadioInline1">Insert</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input autocomplete="off" type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input" value="sua" ${updateChecked}>
						  <label class="custom-control-label" for="customRadioInline2">Update</label>
						</div>
                      </div>
                    </div>
                    
                  </div>
                  <button class="btn btn-primary" name="${chucnang}" type="submit">Submit</button>
                  
                </div>
                

                
              </form:form>
            </div>
          </div>
        </div>
      </div>
      <!-- Footer -->
      
    </div>
      
  <!-- Button trigger modal -->
<button hidden="" id="aaa" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Warning!</h5>
        <button id="bbb" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Việc xóa Lớp học này cũng sẽ xóa toàn bộ sinh viên thuộc lớp đó. Bạn có chắc muốn xóa?
      </div>
      <div class="modal-footer">
        <button type="button" id="ddd" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" id="ccc" class="btn btn-primary"> <a style="color:white" id="no" href=""> OK </a> </button>
      </div>
    </div>
  </div>
</div>

<div class="col-md-4">
      <button hidden="" id="kkk" type="button" class="btn btn-block btn-default" data-toggle="modal" data-target="#modal-form">Form</button>
      <div class="modal fade" id="modal-form" tabindex="-1" role="dialog" aria-labelledby="modal-form" aria-hidden="true">
    <div class="modal-dialog modal- modal-dialog-centered modal-sm" role="document">
        <div class="modal-content">
        	
            <div class="modal-body p-0">
            	
                	

    <!-- <div class="card card-profile"> -->
            <img src="resources/assets/dist/img/theme/img-1-1000x600.jpg" alt="Image placeholder" class="card-img-top">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  
                  <img src="resources/assets/dist/img/theme/team-4.jpg" class="rounded-circle">
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              <div class="d-flex justify-content-between">
                
              </div>
            </div>
            <div class="card-body pt-0">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center">
                    <div>
                      <span class="heading">${NV.tendn}</span>
                      <span class="description">(${NV.manv})</span>
                    </div>
                    
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h5 class="h3">
                  ${NV.hoten}<span class="font-weight-light"></span>
                </h5>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>${NV.email}
                </div>
                
              </div>
            </div>
          <!-- </div> -->
    




                
            </div>
            
        </div>
    </div>
</div>

  </div>

  <!-- Argon Scripts -->
  
  
  <!-- Core -->
  <script src="resources/assets/dist/vendor/jquery/dist/jquery.min.js"></script>
  <script src="resources/assets/dist/vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="resources/assets/dist/vendor/js-cookie/js.cookie.js"></script>
  <script src="resources/assets/dist/vendor/jquery.scrollbar/jquery.scrollbar.min.js"></script>
  <script src="resources/assets/dist/vendor/jquery-scroll-lock/dist/jquery-scrollLock.min.js"></script>
  <!-- Optional JS -->
  <script src="resources/assets/dist/vendor/chart.js/dist/Chart.min.js"></script>
  <script src="resources/assets/dist/vendor/chart.js/dist/Chart.extension.js"></script>
  <!-- Argon JS -->
  <script src="resources/assets/dist/js/argon.js?v=1.2.0"></script>
 
  <script>
  	var btn = document.getElementById("ooo");
  	${clk}
  	
  	function theFunction () {
        // return true or false, depending on whether you want to allow the `href` property to follow through or not
        var btn2 = document.getElementById("aaa");
        
        btn2.click();
        document.getElementById("no").href=h;
        return false;
    }
  	
  	function theFunction2 () {
        // return true or false, depending on whether you want to allow the `href` property to follow through or not
        var btn2 = document.getElementById("kkk");
        
        btn2.click();
        return false;
    }
  	
  </script>
  
</body>
</html>