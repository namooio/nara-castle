<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign in</title>

    <link rel="stylesheet" href="${ctx}/webjars/bootstrap/3.2.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/css/castle.css" />

    <script src="${ctx}/webjars/jquery/2.1.1/jquery.min.js" ></script>
    <script src="${ctx}/webjars/bootstrap/3.2.0/js/bootstrap.min.js" ></script>
    <script src="${ctx}/webjars/react/0.14.7/react.js" ></script>
    <script src="${ctx}/webjars/react/0.14.7/react-dom.js" ></script>
    <script src="https://fb.me/JSXTransformer-0.13.3.js" ></script>

    <script src="${ctx}/js/castle-common.js" ></script>
    <script src="${ctx}/js/castle-router.js" ></script>

    <script src="${ctx}/js/main.jsx" type="text/jsx"></script>
</head>
<body>
    <header id="castle-top-menu">
    </header>

    <section id="castle-content">
    </section>

</body>
</html>
