class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$controller/display/$id"{
            action = [GET:"display"]
        }

        "/$controller/modify/$id"{
            action = [PUT:"modify"]
        }

        //TODO: ask Ruben about 'create' action
        /*"/$controller/new/$id"{
            action = [POST:"new"]
        }*/

        //TODO: ask Ruben about 'remove' action
        /*"/$controller/remove/$id"{
            action = [DELETE:"remove"]
        }*/

 		//"/"(view:"/index")
        //"/users"(resources:'user')
        "/"(controller: 'main')
        "500"(view:'/error')
	}
}
