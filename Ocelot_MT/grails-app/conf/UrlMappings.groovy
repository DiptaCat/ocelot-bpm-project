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


		"/"(controller: 'main')

		"500"(view: '/error')

		//TODO: palette view should be changed
		'/palette'(view: '/palette/index')

		// REST API

        "/api/model" {
            controller = 'model'
            action = [GET: 'index', POST: 'save']
        }

        "/api/model/$id" {
            controller = 'model'
            action = [GET: 'show', PUT: 'update', POST: 'update', DELETE: 'delete']
        }

        "/api/model/export/$id" {
            controller = 'model'
            action = [POST: 'exportToFile']
        }

        "/api/models" {
            controller = 'model'
            action = [GET: 'list']
        }

        "/api/model/export/$id" {
            controller = 'model'
            action = [GET: 'export']
        }

        "/api/category"(controller: 'categoryItem', action: 'index', method: 'GET')

        "/api/palette/$id" {
            controller = 'palette'
            action = [GET: 'show', PUT: 'update', POST: 'update', DELETE: 'delete']
        }

        //TODO decide if url should be paletteItem or customPaletteItem
        "/api/palette/$id/paletteItem" {
            controller = 'customPaletteItem'
            action = [POST: 'save']
        }

		"/api/palette/$id/paletteItem/$itemId" {
			controller = 'customPaletteItem'
			action = [GET: 'show', PUT: 'update', POST: 'update', DELETE: 'delete']
		}





    }
}
