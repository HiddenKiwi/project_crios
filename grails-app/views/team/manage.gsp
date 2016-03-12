<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${resource(dir: 'semantic/dist', file: 'semantic.css')}" type="text/css" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'container.css')}" type="text/css" />
    <g:javascript src="jquery-1.11.3.js"/>
    <script type="text/javascript" src="${resource(dir: 'semantic/dist', file: 'semantic.js')}"></script>
    <title>Manage</title>
</head>

<body>

    <div class="ui container text">
        <h1 class="ui dividing header center aligned"><g:message code="default.manage.team.title" /></h1>
    </div>

    <div class="ui container">
        <div class="ui centered grid">
            <g:link action="search" class="ui green button">
                <i class="icon plus"></i>
                Ajouter une équipe
            </g:link>
        </div>
    </div>

    <div class="ui container">
        <div class="ui centered grid">
            <div class="thirteen wide column">
                <div class="ui cards">
                    <g:each in="${teams}" var="team" status="i">
                        <div class="card">
                            <div class="content">
                                <img class="right floated ui avatar image" src="https://s3.eu-central-1.amazonaws.com/pcstorage/pcsladders/img/avatar_pcs.png">
                                <div class="header">
                                    ${team.name}
                                </div>
                                <div class="meta">
                                    ${team.status}
                                </div>
                                <div class="description"></div>
                            </div>
                            <div class="extra content">
                                <div class="ui two buttons">
                                    <g:link action="statut" id="${team.id}" class="ui basic green button">Statut</g:link>
                                    <g:link action="remove" id="${team.id}" class="ui basic red button">Supprimer</g:link>
                                </div>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>

</body>
</html>