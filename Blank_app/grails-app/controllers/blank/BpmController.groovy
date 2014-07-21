package blank

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BpmController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def recents = {
        respond Bpm.list(sort: "lastUpdated", order: "desc", max: 5)
    }

    def temporals = {
        respond Bpm.list(sort: "temporal", order: "desc").takeWhile { it.temporal }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bpm.list(params), model: [bpmInstanceCount: Bpm.count()]
    }

    def show(Bpm bpmInstance) {
        respond bpmInstance
    }

    def create() {
        respond new Bpm(params)
    }

    @Transactional
    def save(Bpm bpmInstance) {
        if (bpmInstance == null) {
            notFound()
            return
        }

        if (bpmInstance.hasErrors()) {
            respond bpmInstance.errors, view: 'create'
            return
        }

        bpmInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bpm.label', default: 'Bpm'), bpmInstance.id])
                redirect bpmInstance
            }
            '*' { respond bpmInstance, [status: CREATED] }
        }
    }

    def edit(Bpm bpmInstance) {
        respond bpmInstance
    }

    @Transactional
    def update(Bpm bpmInstance) {
        if (bpmInstance == null) {
            notFound()
            return
        }

        if (bpmInstance.hasErrors()) {
            respond bpmInstance.errors, view: 'edit'
            return
        }

        bpmInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Bpm.label', default: 'Bpm'), bpmInstance.id])
                redirect bpmInstance
            }
            '*' { respond bpmInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Bpm bpmInstance) {

        if (bpmInstance == null) {
            notFound()
            return
        }

        bpmInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Bpm.label', default: 'Bpm'), bpmInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bpm.label', default: 'Bpm'), params.id])
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
                   "bpms": {
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
        Bpm bpm = new Bpm(name: getBackJsonObj.bpms.name,
                        dateCreated: Date().parse("E MMM dd H:m:s z yyyy", getBackJsonObj.users.dateCreated),
                        lastUpdated: Date().parse("E MMM dd H:m:s z yyyy", getBackJsonObj.users.lastUpdated),
                        user: owner)
    }
}
