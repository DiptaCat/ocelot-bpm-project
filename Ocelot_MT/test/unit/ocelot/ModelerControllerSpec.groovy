package ocelot


import grails.test.mixin.*
import spock.lang.*

@TestFor(ModelerController)
@Mock(Modeler)
class ModelerControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
	}

	void "Test the index action returns the correct model"() {

		when: "The index action is executed"
		controller.index()

		then: "The model is correct"
		!model.modellerInstanceList
		model.modellerInstanceCount == 0
	}

	void "Test the create action returns the correct model"() {
		when: "The create action is executed"
		controller.create()

		then: "The model is correctly created"
		model.modellerInstance != null
	}

	void "Test the save action correctly persists an instance"() {

		when: "The save action is executed with an invalid instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'POST'
		def modeller = new Modeler()
		modeller.validate()
		controller.save(modeller)

		then: "The create view is rendered again with the correct model"
		model.modellerInstance != null
		view == 'create'

		when: "The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		modeller = new Modeler(params)

		controller.save(modeller)

		then: "A redirect is issued to the show action"
		response.redirectedUrl == '/modeller/show/1'
		controller.flash.message != null
		Modeler.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when: "The show action is executed with a null domain"
		controller.show(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the show action"
		populateValidParams(params)
		def modeller = new Modeler(params)
		controller.show(modeller)

		then: "A model is populated containing the domain instance"
		model.modellerInstance == modeller
	}

	void "Test that the edit action returns the correct model"() {
		when: "The edit action is executed with a null domain"
		controller.edit(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the edit action"
		populateValidParams(params)
		def modeller = new Modeler(params)
		controller.edit(modeller)

		then: "A model is populated containing the domain instance"
		model.modellerInstance == modeller
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when: "Update is called for a domain instance that doesn't exist"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'PUT'
		controller.update(null)

		then: "A 404 error is returned"
		response.redirectedUrl == '/modeller/index'
		flash.message != null


		when: "An invalid domain instance is passed to the update action"
		response.reset()
		def modeller = new Modeler()
		modeller.validate()
		controller.update(modeller)

		then: "The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.modellerInstance == modeller

		when: "A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		modeller = new Modeler(params).save(flush: true)
		controller.update(modeller)

		then: "A redirect is issues to the show action"
		response.redirectedUrl == "/modeller/show/$modeller.id"
		flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when: "The delete action is called for a null instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'DELETE'
		controller.delete(null)

		then: "A 404 is returned"
		response.redirectedUrl == '/modeller/index'
		flash.message != null

		when: "A domain instance is created"
		response.reset()
		populateValidParams(params)
		def modeller = new Modeler(params).save(flush: true)

		then: "It exists"
		Modeler.count() == 1

		when: "The domain instance is passed to the delete action"
		controller.delete(modeller)

		then: "The instance is deleted"
		Modeler.count() == 0
		response.redirectedUrl == '/modeller/index'
		flash.message != null
	}
}