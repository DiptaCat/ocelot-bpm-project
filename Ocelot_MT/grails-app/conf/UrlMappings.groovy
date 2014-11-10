class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/models" {
			controller = 'model'
			action = [GET: 'index', POST: 'save']
		}


		//TODO: change display with show
        "/api/models/$id" {
			controller = 'model'
            action = [GET: 'display', POST: 'update', PUT: 'update', DELETE: 'delete']
        }

 		//"/"(view:"/index")
        //"/users"(resources:'user')
        "/"(controller: 'main')
        "500"(view:'/error')
	}
}
