<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title><g:message code="default.form.add.team.title" /></title>
    <link rel="stylesheet" href="${resource(dir: 'semantic/dist', file: 'semantic.css')}" type="text/css" />
    <g:javascript src="jquery-1.11.3.js"/>
    <script type="text/javascript" src="${resource(dir: 'semantic/dist', file: 'semantic.js')}"></script>

    <style>
        .container {
            padding: 3em 0em;
        }

        .error-message {
            text-align: left;
        }
        .summoner-name {
            font-weight: bold;
        }
    </style>
</head>

<body>
    <div class="ui text container">
        <h1 class="ui dividing header center aligned"><g:message code="default.form.add.team.title" /></h1>
    </div>

    <g:if test="${addResults != null && addResults.size() != 0}">
        <div class="ui container">
            <g:each in="${addResults}" var="addResult" status="i">

                <g:if test="${addResult.isSuccess}">
                        <div class="ui positive message">
                            <i class="close icon"></i>
                            <div class="header">
                                <g:message code="default.form.add.team.success.add.title"/> ${addResult.teamName}
                            </div>
                            <p><g:message code="default.form.add.team.success.add.message"/></p>
                        </div>
                </g:if>

                <g:else>
                    <div class="ui error message">
                        <i class="close icon"></i>
                        <div class="header"><g:message code="default.form.add.team.error.add.title"/> ${addResult.teamName}</div>
                        <div class="error-message">
                            <g:if test="${addResult.errorCode.equals('unique')}">
                                <p><g:message code="default.form.add.team.error.add.unique"/></p>
                            </g:if>
                            <g:else>
                                <p><g:message code="default.form.add.team.error.add.other"/> ${addResult.errorCode}</p>
                            </g:else>
                        </div>
                    </div>
                </g:else>
            </g:each>
        </div>
    </g:if>

    <g:if test="${searchHasErrors}">
        <div class="ui container">
            <div class="ui error message">
                <i class="close icon"></i>
                <div class="header" data-error="${code}"><g:message code="default.form.add.team.error.summoner.title"/></div>
                <div class="error-message">
                    <p><g:message code="default.form.add.team.error.summoner.message"/></p>
                    <ul class="list">
                        <li><span class="summoner-name">"${summonerName}"</span> <g:message code="default.form.add.team.error.summoner.invalid.name"/></li>
                        <li><g:message code="default.form.add.team.error.summoner.riot.errors"/></li>
                    </ul>
                    <p><g:message code="default.form.add.team.error.contact"/></p>
                </div>
            </div>
        </div>
    </g:if>

    <g:if test="${searchNull}">
        <div class="ui container">
            <div class="ui error message">
                <i class="close icon"></i>
                <div class="header" data-error="${code}"><g:message code="default.form.add.team.error.summoner.title"/></div>
                <div class="error-message">
                    <p><g:message code="default.form.add.team.error.summoner.null"/></p>
                </div>
            </div>
        </div>
    </g:if>

    <div class="ui container">
        <div class="ui grid">
            <div class="ten wide column centered row">
                <g:form url="[controller:'team', action: 'summoner']" name="teamResearch" class="ui form error">
                    <div class="inline fields">
                        <div class="eight wide field">
                            <g:render template="form-add"/>
                        </div>
                        <div class="two wide field">
                            <button type="submit" class="ui button">
                                <g:message code="default.form.add.team.button.search.team.label"/>
                            </button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <g:if test="${summonerTeams != null && summonerTeams.size() != 0}">
        <div class="ui container">
            <div class="ui grid">
                <div class="centered row">
                    <div class="ui floating message">
                        <g:form id="formAddTeam" url="[controller: 'team', action: 'add']" name="team-add" class="ui form">
                            <g:each in="${summonerTeams}" var="team" status="i">
                                <div class="inline fields">
                                    <div class="field">
                                        <input name="teamsToAdd" type="checkbox" value="${team.teamId}" tabindex="0" class="hidden">
                                        <label>${team.name}</label>
                                    </div>
                                </div>
                            </g:each>
                            <button type="submit" class="ui button">
                                <g:message code="default.form.add.team.button.add.team.label"/>
                            </button>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </g:if>

</body>
</html>