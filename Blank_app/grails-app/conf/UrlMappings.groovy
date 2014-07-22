class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

 		//"/"(view:"/index")
        //"/users"(resources:'user')
        "/"(controller: 'main')
        "500"(view:'/error')
	}
}
