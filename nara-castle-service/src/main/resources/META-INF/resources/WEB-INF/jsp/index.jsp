<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="castingId" content="${castingId}"/>
    <meta name="playerId" content="${playerId}"/>
    <title>Castle</title>

    <!--
    <link rel="stylesheet" href="./resources/webjars/bootstrap/3.3.6/css/bootstrap.css" />
    <link rel="stylesheet" href="./resources/css/castle.css" />

    <script src="./resources/webjars/jquery/2.1.3/jquery.js" ></script>
    <script src="./resources/webjars/bootstrap/3.3.6/js/bootstrap.js" ></script>
    <script src="./resources/webjars/react/0.14.7/react.js" ></script>
    <script src="./resources/webjars/react/0.14.7/react-dom.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.34/browser.js"></script>
    <script src="./resources/js/lib/nara-common.js" ></script>
    <script src="./resources/js/lib/nara-react-router.js" ></script>

    <script src="./resources/js/common/castle-common.js" ></script>
    <script src="./resources/js/common/castle-router.js" ></script>


    <script src="./resources/js/component/common/top-menu.jsx" type="text/babel"></script>
    <script src="./resources/js/component/common/main.jsx" type="text/babel"></script>
    -->

    <link rel="stylesheet" href="${ctx}/resources/webjars/bootstrap/3.3.6/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/castle.css" />

</head>
<body>
    <main id="castle-drama">
    </main>


    <!--<script src="/resources/webjars/bootstrap/3.3.6/js/bootstrap.js" ></script>-->
    <script src="${ctx}/resources/js/lib-bundle.js"></script>
    <script src="${ctx}/resources/js/app-bundle.js"></script>

</body>
</html>
