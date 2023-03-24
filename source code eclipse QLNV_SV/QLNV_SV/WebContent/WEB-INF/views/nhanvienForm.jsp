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
  <title>Form sinh viên</title>
  
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
              <a class="nav-link active" href="QLNV.htm">
                <i class="ni ni-circle-08 text-primary"></i>
                <span class="nav-link-text">Quản lý nhân viên</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="QLLH.htm">
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
    <div class="header bg-primary pb-6">
      <div class="container-fluid">
        <div class="header-body">
          <div class="row align-items-center py-4">
          
            <div class="col-lg-6 col-7">
              <h6 class="h2 text-white d-inline-block mb-0">Welcome</h6>
              <nav aria-label="breadcrumb" class="d-none d-md-inline-block ml-md-4">
                <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                  <li class="breadcrumb-item"><a href="QLNV.htm"><i class="fas fa-home"></i></a></li>
                  <li class="breadcrumb-item"><a href="QLNV.htm">Quản Lý Nhân Viên / Form</a></li>
                  
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
        
        <div class="col-xl-12 order-xl-1">
          <div class="card">
            <div class="card-header">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">Edit Nhan Vien: <c:out value="${message1}"/> <c:out value="${message2}"/>  </h3>
                </div>
                <div class="col-4 text-right">
                  
                </div>
              </div>
            </div>
            <div class="card-body">
              <form:form action="themSuaNhanVien${kt}${MANV}.htm" method="post" modelAttribute="nhanvien" autocomplete="off">
                <h6 class="heading-small text-muted mb-4">Staff information</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-username">Mã nhân viên</label>
                        <form:input autocomplete="off" type="text" id="input-username" class="form-control" placeholder="MANV" required="required" pattern="NV[0-9]{2}" title="NV?? example NV01, NV02" value="${nhanvien2.manv}" path="manv"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-email">Email address</label>
                        <form:input type="email" id="input-email" class="form-control" placeholder="EMAIL@" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="email@example.com, length no more than 20" maxlength="20" value="${nhanvien2.email}"  path="email" autocomplete="off"/>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-first-name">Họ tên</label>
                        <form:input autocomplete="off" type="text" id="input-first-name" class="form-control" placeholder="HOTEN" pattern="[A-za-z\s]{1,99}" maxlength="99" title=" no special characters, numbers" required="required" value="${nhanvien2.hoten}" path="hoten"/>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-last-name">Lương</label>
                        <form:input autocomplete="off" type="text" id="input-last-name" class="form-control" placeholder="LUONG" required="required" pattern="[0-9]{1,}" title="only numeric characters" value="${nhanvien2.luong}" path="luong" />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-first-name">Tên đăng nhập</label>
                        <form:input autocomplete="off" type="text" id="input-first-name" class="form-control" placeholder="TENDN" required="required" pattern="[A-za-z0-9]{1,99}" maxlength="99" title="no special characters" value="${nhanvien2.tendn}" path="tendn" />
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-last-name">Mật khẩu</label>
                        <form:input autocomplete="off" type="text" id="input-last-name" class="form-control" placeholder="MATKHAU" required="required" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character" path="matkhau" />
                      </div>
                    </div>
                  </div>
                  
                  <button class="btn btn-primary" type="submit" name="${chucnang}">Submit</button>
                </div>
                
              </form:form>
            </div>
          </div>
        </div>
      </div>
      <!-- Footer -->
      
    </div>
  </div>
    
    
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
  	
  	function theFunction2 () {
        // return true or false, depending on whether you want to allow the `href` property to follow through or not
        var btn2 = document.getElementById("kkk");
        
        btn2.click();
        return false;
    }
  </script>
  
  
</body>
</html>