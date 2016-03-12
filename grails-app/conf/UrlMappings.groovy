class UrlMappings {

	static mappings = {

            "/$controller/$action?/$id?"{
                    constraints {
                            // apply constraints here
                    }
            }

        "/classement/titulaire/5c5/palier/index"(controller: "teamRanking", action:"indexTitulaireTierRift")
        "/classement/school/5c5/palier/index"(controller: "teamRanking", action:"indexSchoolTierRift")
        "/equipes/index"(controller: "team", action: "index")

        "/equipe/recherche"(controller: "team", action: "search")
        "/equipe/ajout/"(controller: "team", action: "summoner")
        "/equipe/ajout/resultat"(controller: "team", action: "add")

        "/"(view:"/index")
        //"/"(controller: "secure", action: "index")
        "500"(view:'/error')
	}
}
