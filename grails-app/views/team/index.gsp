
<%@ page import="pcsladders.team.Team" %>
<!DOCTYPE html>
<html>
	<head>
		<title><g:message code="default.index.team.title" /></title>
		<link rel="stylesheet" href="${resource(dir: 'semantic/dist', file: 'semantic.css')}" type="text/css" />
		<g:javascript src="jquery-1.11.3.js"/>
		<script type="text/javascript" src="${resource(dir: 'semantic/dist', file: 'semantic.js')}"></script>
		<style>

			.container {
				padding: 3em 0em;
			}

			.criterias{
				margin-top: 1px;
			}

			.shape {
				margin: 1px;
			}

			[data-color='Bronze'] {
				color: #614E1A;
			}

			[data-color='Silver'] {
				color: #AFAFAF;
			}

			[data-color='Gold'] {
				color: #DFAF2C;
			}

			[data-color='Platinium'] {
				color: #56739A;
			}

			[data-color='Diamond'] {
				color: #77B5FE;
			}

			[data-color='Master'] {
				color: #BBD2E1;
			}

			[data-color='Challenger'] {
				color: #ED7F10;
			}

		</style>
		<script>


			$(document).ready(function(){

				$('.ui.checkbox').checkbox('check');

				$('.ui.checkbox').click(function(){
					var isChecked = $(this).checkbox('is checked');
					var criteria = $(this).children()[0].attributes.getNamedItem("name").nodeValue;
					var value = $(this).children()[0].attributes.getNamedItem("data-criteria-value").nodeValue

					if(isChecked) {
						$('[data-' + criteria + '="' + value + '"]').fadeIn(600);
					}
					else {
						$('[data-' + criteria + '="' + value + '"]').fadeOut(600);
					}
				});

			});

		</script>
	</head>
	<body>

		<div class="ui text container">
			<h1 class="ui dividing header center aligned"><g:message code="default.index.team.title" /></h1>
		</div>

		<div class="ui container">
			<div class="ui grid">
				<div class="four wide column left floated criterias">
					<h3 class="ui top attached header">
						<g:message code="default.index.team.criteria" />
					</h3>
					<div class="ui attached segment">
						<div class="ui form">
							<div class="grouped fields">
								<label><g:message code="default.index.team.criteria.status" /></label>

								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="status" data-criteria-value="Titulaire" checked="">
										<label><g:message code="default.index.team.criteria.status.titular" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="status" data-criteria-value="School" checked="">
										<label><g:message code="default.index.team.criteria.status.school" /></label>
									</div>
								</div>

								<label><g:message code="default.index.team.criteria.tier" /></label>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Unranked" checked>
										<label><g:message code="default.index.team.criteria.tier.unranked" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Bronze" checked="">
										<label><g:message code="default.index.team.criteria.tier.bronze" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Silver" checked="">
										<label><g:message code="default.index.team.criteria.tier.silver" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Gold" checked="">
										<label><g:message code="default.index.team.criteria.tier.gold" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Platinium" checked="">
										<label><g:message code="default.index.team.criteria.tier.platinium" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Diamond" checked="">
										<label><g:message code="default.index.team.criteria.tier.diamond" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox">
										<input type="checkbox" name="tier" data-criteria-value="Master" checked="">
										<label><g:message code="default.index.team.criteria.tier.master" /></label>
									</div>
								</div>
								<div class="field">
									<div class="ui checked toggle checkbox" >
										<input type="checkbox" name="tier" data-criteria-value="Challenger" checked="">
										<label><g:message code="default.index.team.criteria.tier.challenger" /></label>
									</div>
								</div>

								<label><g:message code="default.index.team.actions" /></label>
								<div class="field">
									<div class="ui toggle checkbox">
										<input type="checkbox" name="roster" data-direction="back" checked="">
										<label><g:message code="default.index.team.actions.roster" /></label>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="twelve wide column">

					<g:each in="${teamInstanceList}" status="i" var="teamInstance">

						<div class="ui people shape" data-tier="${fieldValue(bean: teamInstance, field: "tierRift")}" data-status="${fieldValue(bean: teamInstance, field: "status")}">
							<div class="sides">

								<div class="side active">
									<div class="ui card" style="width: 200px; height: 330px;">
										<div class="content center aligned">
											<h5 class="ui header">${fieldValue(bean: teamInstance, field: "name")}</h5>
										</div>
										<div class="image">
											<img src="https://i.imgur.com/BWqkIRN.png" style="width: 200px; height: 150px;">
										</div>
										<div class="content center aligned">
											<div>
												<h5 class="ui header">5c5 :
													<span data-color="${fieldValue(bean: teamInstance, field: "tierRift")}">
														${fieldValue(bean: teamInstance, field: "tierRift")}
														${fieldValue(bean: teamInstance, field: "divisionRift")}
													</span>
												</h5>
											</div>
											<div class="ui tiny statistics" style="margin-left: -7px;">
												<div class="green statistic">
													<div class="value">
														${fieldValue(bean: teamInstance, field: "loosesRift")}
													</div>
													<div class="label">
														Wins
													</div>
												</div>
												<div class="red statistic">
													<div class="value">
														${fieldValue(bean: teamInstance, field: "winsRift")}
													</div>
													<div class="label">
														Looses
													</div>
												</div>
											</div>
										</div>
										<div class="extra content">
											<span class="date meta right floated">Created in 2014</span>
										</div>
									</div>
								</div>

								<div class="side">
									<div class="ui card" style="width: 200px;">
										<div class="content">
											<h5 class="ui header">${fieldValue(bean: teamInstance, field: "name")}</h5>
										</div>
										<div class="content">
											<h4 class="ui sub header center aligned">Roster</h4>
											<div class="ui small feed">
												<div class="content" style="height: 240px;">
														<p>Player 1</p>
														<p>Player 2</p>
														<p>Player 3</p>
														<p>Player 4</p>
														<p>Player 5</p>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>

					</g:each>

				</div>

			</div>

		</div>

	</body>
</html>