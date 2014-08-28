package blank

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.*

@Transactional(readOnly = true)
class UserController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[userInstanceList: User.list(params), userInstanceCount: User.count ]
	}

    def show(User userInstance) {
        respond userInstance
    }

    def display(User userInstance) {
        if(params.id){
            if (userInstance) render userInstance as XML
            else render(status: 404, text: 'User not found')
        }
        else render User.list() as XML
    }

    def create() {
		respond new User(params)
    }

    @Transactional
    def save(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    def markFavourite() {

        User userInstance = User.get(params.userId)
        Model model = Model.get(params.modelId)
		model.favourite=true
        model.save(flush: true)

        respond userInstance, view: 'getModelTabs'
    }

    def unmarkFavourite() {

		User userInstance = User.get(params.userId)
		Model model = Model.get(params.modelId)
		model.favourite=false
		model.save(flush: true)

        respond userInstance, view: 'getModelTabs'
    }

    def getModels(User userInstance) {
        if(userInstance)
            respond userInstance
        else
            redirect action: "index", method: "GET"
    }

    def getModelTabs(User userInstance) {
        respond userInstance
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}