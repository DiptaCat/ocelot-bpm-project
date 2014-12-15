class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?" {
			constraints {
				// apply constraints here
			}
		}

		"/$controller/display/$id" {
			action = [GET: "display"]
		}

		"/$controller/modify/$id" {
			action = [PUT: "modify"]
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
		"500"(view: '/error')

		//TODO: palette view should be changed
		'/palette'(view: '/palette/index')

		// RESTService api
//        "/api/palette" (controller: 'palette', action: 'index', method: 'GET')
//        "/api/palette/$id" (controller: 'palette', action: 'show', method: 'GET')


		"/api/palette/$id" {
			controller = 'palette'
			action = [GET: 'show', PUT: 'update', POST: 'update', DELETE: 'delete']
		}

		"/api/category"(controller: 'categoryItem', action: 'index', method: 'GET')

		//"/api/paletteItem/" (controller: 'paletteItem', action: 'save', method: 'POST')

		"/api/palette/$id/paletteItem/$id" {
			controller = 'paletteItem'
			action = [GET: 'show', PUT: 'update', POST: 'update', POST: 'save', DELETE: 'delete']
		}

		"/api/export/$modelId" {
			controller = 'model'
			action = [POST: 'exportToFile']
		}
	}
}
