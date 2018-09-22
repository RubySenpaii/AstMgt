<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>



<c:choose>     
    <c:when test="${user.group eq 'Board'}">
         <%@include file ="navboard.jsp" %>
      </c:when>
    <c:when test="${user.group eq 'MDO'}">
        <%@include file ="navbar.jsp" %>
      </c:when>
    <c:when test="${user == null}">
       <c:redirect url="index.jsp"/>
    </c:when>
</c:choose>
