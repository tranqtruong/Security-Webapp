<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Start your development with a Dashboard for Bootstrap 4.">
  <meta name="author" content="Creative Tim">
  <meta name="referrer" content="strict-origin-when-cross-origin" />
  <base href="${pageContext.servletContext.contextPath}/">
  
  <title>Change password</title>
  
  <!-- Favicon -->
  <link href="<c:url value='/resources/assets/dist/img/brand/favicon.png'/>" rel="image/png">
  <!-- Fonts -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
  <!-- Icons -->
  <link href="<c:url value='/resources/assets/dist/vendor/nucleo/css/nucleo.css'/>" rel="stylesheet">
  
  <link href="<c:url value='/resources/assets/dist/vendor/@fortawesome/fontawesome-free/css/all.min.css'/>" rel="stylesheet">
  <!-- Argon CSS -->
  
  <link href="<c:url value='/resources/assets/dist/css/argon.css?v=1.2.0'/>" rel="stylesheet">
</head>

<body class="bg-default">
  
  <!-- Main content -->
  <div class="main-content">
    <!-- Header -->
    <div class="header bg-gradient-primary py-7 py-lg-8 pt-lg-9">
      <div class="container">
        <div class="header-body text-center mb-7">
          <div class="row justify-content-center">
            <div class="col-xl-5 col-lg-6 col-md-8 px-5">
              <h1 class="text-white">Change Your Password</h1>
              <p class="text-lead text-white">Use these awesome forms to change password in your project for free.</p>
            </div>
          </div>
        </div>
      </div>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <div class="container mt--8 pb-5">
      <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">
          <div class="card bg-secondary border-0 mb-0">
            
            <div class="card-body px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>Change Password</small>
              </div>
              
              <form role="form" action="changepassword.htm" method="post" autocomplete="off">
                <div class="form-group mb-3">
                  <div class="input-group input-group-merge input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-circle-08"></i></span>
                    </div>
                    <input class="form-control" name="username1" placeholder="Username" type="text" value="${tendnn}"  required disabled="disabled" autocomplete="off">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="input-group input-group-merge input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input class="form-control" name="currentpassword" placeholder="Current Password" type="password" required autocomplete="off">
                  </div>
                </div>
                
                <hr>
                
                <div class="form-group">
                  <div class="input-group input-group-merge input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input class="form-control" name="newpassword" placeholder="New Password" type="password" required autocomplete="off" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="input-group input-group-merge input-group-alternative">
                    <div class="input-group-prepend">
                      <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                    </div>
                    <input class="form-control" name="confirmpassword" placeholder="Confirm Password" type="password" required autocomplete="off" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" title="Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character">
                  </div>
                </div>
                
                
                <div class="text-center">
                	
                	
                	
                	<h5><c:out value="${message}"/> </h5>
                  <button type="submit" class="btn btn-primary my-4" >Change pass</button>
                </div>
              </form>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-6">
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Footer -->
  <footer class="py-5" id="footer-main">
    <div class="container">
      <div class="row align-items-center justify-content-xl-between">
        <div class="col-xl-6">
          <div class="copyright text-center text-xl-left text-muted">
            &copy; 2020 <a href="" class="font-weight-bold ml-1" target="_blank">Creative Tim</a>
          </div>
        </div>
        <div class="col-xl-6">
          <ul class="nav nav-footer justify-content-center justify-content-xl-end">
            <li class="nav-item">
              <a href="" class="nav-link" target="_blank">Creative Tim</a>
            </li>
            <li class="nav-item">
              <a href="" class="nav-link" target="_blank">About Us</a>
            </li>
            <li class="nav-item">
              <a href="" class="nav-link" target="_blank">Blog</a>
            </li>
            <li class="nav-item">
              <a href="" class="nav-link" target="_blank">MIT License</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
  <!-- Argon Scripts -->
  <!-- Core -->
  <script src="/resources/assets/dist/vendor/jquery/dist/jquery.min.js"></script>
  <script src="/resources/assets/dist/vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="/resources/assets/dist/vendor/js-cookie/js.cookie.js"></script>
  <script src="/resources/assets/dist/vendor/jquery.scrollbar/jquery.scrollbar.min.js"></script>
  <script src="/resources/assets/dist/vendor/jquery-scroll-lock/dist/jquery-scrollLock.min.js"></script>
  <!-- Argon JS -->
  <script src="/resources/assets/dist/js/argon.js?v=1.2.0"></script>
  
 
</body>
</html>
