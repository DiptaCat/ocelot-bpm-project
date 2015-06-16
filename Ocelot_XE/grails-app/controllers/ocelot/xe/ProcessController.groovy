package ocelot.xe

import org.camunda.bpm.engine.form.FormField
import org.camunda.bpm.engine.repository.ProcessDefinition

class ProcessController {

	def workflowService

	def index() {

		def processDefinitions = workflowService.getDeployments()
		def data = []
		processDefinitions.each { ProcessDefinition p ->

			def numInstances = workflowService.countProcessInstances(p.id)
			def exp = new Expando(id: p.id, name: p.name, version: p.version, numInstances: numInstances)
			data << exp
		}

		[processes: data]
	}

	def show() {
		def processDefinition = workflowService.getProcessDefinitionById(params.id)

		def numInstances = workflowService.countProcessInstances(processDefinition.id)
		[process: processDefinition, numInstances: numInstances]
	}

	def create(String id) {

		def processDefinition = workflowService.getProcessDefinitionById(id)

		def startFormData = workflowService.getStartFormData(id)
		render(view: 'create.gsp', model: [startFormData: startFormData, processDefinitionId: processDefinition.id])
	}

	def save() {

		def startFormData = workflowService.getStartFormData(params.id)

		def vars = [:]
		startFormData.each { FormField d ->
			d.defaultValue = params[d.id]
			vars.put(d.id, d.defaultValue)
		}

		workflowService.startProcess(params.id, session.user, vars)

		redirect(action: 'index')

	}

	def upload() {
		def f = request.getFile('myFile')
		//def f = request.

		if (f.empty) {
			flash.error = 'ERROR: File cannot be empty'
			redirect(action: 'index')
			return
		}
		def fileContent = f.getInputStream()
		try {
			workflowService.deployProcess(fileContent, f.originalFilename)
			flash.message = 'The file was uploaded'

		} catch (Exception e) {
			flash.error = e.message//'ERROR: File cannot be read'
		}
		redirect(action: 'index')
	}


}
