<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}/drama/castle" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="pavilionId" content="${pavilionId}"/>
    <meta name="castingId" content="${castingId}"/>
    <meta name="playerId" content="${playerId}"/>
    <title>Castle</title>

    <link rel="stylesheet" href="${ctx}/resources/webjars/bootstrap/3.3.6/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/castle.css" />
</head>
<body>
    <main id="castle-drama">
    </main>

    <script src="${ctx}/resources/js/lib-bundle.js"></script>
    <script src="${ctx}/resources/js/app-bundle.js"></script>

</body>
</html>
