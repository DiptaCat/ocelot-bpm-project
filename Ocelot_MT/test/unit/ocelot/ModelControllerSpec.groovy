package ocelot

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ModelController)
@Mock(Model)
class ModelControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null
		params["name"] = 'Proj1'
		params["user"] = new Member()
	}

	void "Test the index action returns the correct model"() {

		when: "The index action is executed"
		def cntrl = controller.index()

		then: "The model is correct"
		!cntrl.modelInstanceList
		cntrl.modelInstanceCount == 0
	}

	void "Test the create action returns the correct model"() {
		when: "The create action is executed"
		controller.create()

		then: "The model is correctly created"
		model.modelInstance != null
	}

	void "Test the save action correctly persists an instance"() {

		when: "The save action is executed with an invalid instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'POST'
		def mdl = new Model()
		mdl.validate()
		controller.save()

		then: "The create view is rendered again with the correct model"
		model.modelInstance != null
		view == 'create'

		when: "The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		mdl = new Model(params)

		controller.save()

		then: "A redirect is issued to the show action"
		response.redirectedUrl == '/model/show/1'
		controller.flash.message != null
		Model.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when: "The show action is executed with a null domain"
		controller.show(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the show action"
		populateValidParams(params)
		def mdl = new Model(params)
		controller.show(mdl)

		then: "A model is populated containing the domain instance"
		model.modelInstance == mdl
	}

	void "Test that the edit action returns the correct model"() {
		when: "The edit action is executed with a null domain"
		controller.edit(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the edit action"
		populateValidParams(params)
		def mdl = new Model(params)
		controller.edit(mdl)

		then: "A model is populated containing the domain instance"
		model.modelInstance == mdl
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when: "Update is called for a domain instance that doesn't exist"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'PUT'
		controller.update(null)

		then: "A 404 error is returned"
		response.redirectedUrl == '/model/index'
		flash.message != null


		when: "An invalid domain instance is passed to the update action"
		response.reset()
		def mdl = new Model()
		mdl.validate()
		controller.update(mdl)

		then: "The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.modelInstance == mdl

		when: "A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		mdl = new Model(params).save(flush: true)
		controller.update(mdl)

		then: "A redirect is issues to the show action"
		response.redirectedUrl == "/model/show/$mdl.id"
		flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when: "The delete action is called for a null instance"
		request.contentType = FORM_CONTENT_TYPE
		request.method = 'DELETE'
		controller.delete(null)

		then: "A 404 is returned"
		response.redirectedUrl == '/model/index'
		flash.message != null

		when: "A domain instance is created"
		response.reset()
		populateValidParams(params)
		def mdl = new Model(params).save(flush: true)

		then: "It exists"
		Model.count() == 1

		when: "The domain instance is passed to the delete action"
		controller.delete(mdl)

		then: "The instance is deleted"
		Model.count() == 0
		response.redirectedUrl == '/model/index'
		flash.message != null
	}
}
