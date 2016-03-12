<%@ page import="pcsladders.team.Team" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <g:message code="default.ranking.team.rift.school.title"></g:message>
    </title>
    <link rel="stylesheet" href="${resource(dir: 'semantic/dist', file: 'semantic.css')}" type="text/css" />
    <g:javascript src="jquery-1.11.3.js"/>
    <script type="text/javascript" src="${resource(dir: 'semantic/dist', file: 'semantic.js')}"></script>

    <style>
    .container {
        padding: 3em 0em;
    }
    </style>
</head>

<body>

<div class="ui text container">
    <h1 class="ui dividing header center aligned">
        <g:message code="default.ranking.team.rift.school.title" />
    </h1>
</div>

<div class="ui container">
    <table class="ui blue striped selectable table">
        <thead>
        <tr class="center aligned">
            <th><g:message code="default.ranking.team.header.position" /></th>
            <th><g:message code="default.ranking.team.header.team" /></th>
            <th><g:message code="default.ranking.team.header.tier" /></th>
            <th><g:message code="default.ranking.team.header.division" /></th>
            <th><g:message code="default.ranking.team.header.leaguepoints" /></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${teams}" status="i" var="teamInstance">
            <tr class="center aligned" style="height: 50px;">
                <g:if test='${i==0}'>
                    <td class="left aligned">
                        <div class="ui red ribbon label">
                            <i class="trophy icon"></i>
                            ${fieldValue(bean: teamInstance, field: "rankingTierRift")}
                        </div>
                    </td>
                </g:if>
                <g:else>
                    <td>${fieldValue(bean: teamInstance, field: "rankingTierRift")}</td>
                </g:else>
                <td>${fieldValue(bean: teamInstance, field: "name")}</td>
                <td>
                    <img src="https://s3.eu-central-1.amazonaws.com/pcstorage/pcsladders/img/tier/${fieldValue(bean: teamInstance, field: "tierRift")}"/>
                </td>
                <td>${fieldValue(bean: teamInstance, field: "divisionRift")}</td>
                <td>${fieldValue(bean: teamInstance, field: "leaguePointsRift")}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

</body>
</html>