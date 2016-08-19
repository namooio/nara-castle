<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="pavilionId" content="${pavilionId}"/>
    <meta name="castingId" content="${castingId}"/>
    <meta name="playerId" content="${playerId}"/>
    <title>Castle</title>


    <link rel="stylesheet" href="${ctx}/castle-resource/webjars/bootstrap/3.3.6/css/bootstrap.css" />
    <!--
    <link rel="stylesheet" href="${ctx}/castle-resource/css/castle.css" />
    -->
</head>
<body>
    <main id="castle-drama">
    </main>


    <script src="${ctx}/castle-resource/js/castle-external-lib-bundle.js"></script>
    <script src="${ctx}/castle-resource/js/castle-nara-lib-bundle.js"></script>
    <script src="${ctx}/castle-resource/js/castle-app-bundle.js"></script>

</body>
</html>
