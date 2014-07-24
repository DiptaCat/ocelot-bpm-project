package blank

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ModelController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def recents = {
        respond Model.list(sort: "lastUpdated", order: "desc", max: 5)
    }

    def temporals = {
        respond Model.list(sort: "temporal", order: "desc").takeWhile { it.temporal }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Model.list(params), model: [modelInstanceCount: Model.count()]
    }

    def show(Model modelInstance) {
        respond modelInstance
    }

    def create() {
        respond new Model(params)
    }

    @Transactional
    def save(Model modelInstance) {
        if (modelInstance == null) {
            notFound()
            return
        }

        if (modelInstance.hasErrors()) {
            respond modelInstance.errors, view: 'create'
            return
        }

        modelInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'model.label', default: 'Model'), modelInstance.id])
                redirect modelInstance
            }
            '*' { respond modelInstance, [status: CREATED] }
        }
    }

    def edit(Model modelInstance) {
        respond modelInstance
    }

    @Transactional
    def update(Model modelInstance) {
        if (modelInstance == null) {
            notFound()
            return
        }

        if (modelInstance.hasErrors()) {
            respond modelInstance.errors, view: 'edit'
            return
        }

        modelInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'model.label', default: 'Model'), modelInstance.id])
                redirect modelInstance
            }
            '*' { respond modelInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Model modelInstance) {

        if (modelInstance == null) {
            notFound()
            return
        }

        modelInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Model.label', default: 'Model'), modelInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def jsonToOject() {
        def json = '''{
                  "users": {
                      "login": "dato_st",
                      "name": "Sergi Toda",
                      "dateCreated": "28/09/2010 16:02:43"
                   }
                   "models": {
                      "name": "Ocelot"
                      "dateCreated": "28/09/2010 16:05:43"
                      "lastUpdated": "28/09/2010 16:02:43"
                   }
                }'''

        def jsonObj = JSON.parse(json)
        def jsonStr = jsonObj.toString()
        def getBackJsonObj = JSON.parse(jsonStr)
        User owner = new User(name: getBackJsonObj.users.name,
                login: getBackJsonObj.users.name,
                dateCreated: Date().parse("E MMM dd H:m:s z yyyy", getBackJsonObj.users.dateCreated))
        Model model = new Model(name: getBackJsonObj.models.name,
                        dateCreated: Date().parse("E MMM dd H:m:s z yyyy", getBackJsonObj.users.dateCreated),
                        lastUpdated: Date().parse("E MMM dd H:m:s z yyyy", getBackJsonObj.users.lastUpdated),
                        user: owner)
    }
}
