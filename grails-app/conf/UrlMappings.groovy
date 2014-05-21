class UrlMappings {

	static mappings = {
        "/$controller/$action?/$number?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'koan')
        "500"(view:'/error')
	}
}
