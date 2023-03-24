<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Security-Policy" content="script-src 'self' https://apis.google.com">
<meta name="referrer" content="strict-origin-when-cross-origin" />
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${chon == 1}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/QLLH.htm?check=loimnv"/></c:when>
	<c:when test="${chon == 2}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/QLLH.htm"/></c:when>
	<c:when test="${chon == 3}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/QLLH.htm?check=loimnv"/></c:when>
	<c:when test="${chon == 4}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/goformsv.htm?cn=sua&mess=loimasv"/></c:when>
	<c:when test="${chon == 5}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/QLSV.htm"/></c:when>
	<c:when test="${chon == 6}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/QLLH.htm?check=loimnv2"/></c:when>
	<c:when test="${chon == 7}"><c:redirect url="https://trantruong.com:8443/QLNV_SV/goformsv.htm?cn=sua&mess=robot"/></c:when>
</c:choose>

</body>
</html>