class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'process')
        "500"(view:'/error')

        //rest api

        "/api/deployment/$id" {
            controller = 'rest'
            action = [GET: 'getDeployment']
        }

        "/api/deployments" {
            controller = 'rest'
            action = [GET: 'getDeployments']
        }

        "/api/deployment/create" {
            controller = 'rest'
            action = [POST: 'deployProcess']
        }

        "/api/process/definition/$id" {
            controller = 'rest'
            action = [GET: 'getProcessDefinition']
        }

        "/api/process/start" {
            controller = 'rest'
            action = [GET: 'startProcess']
        }

        "/api/tasks/unassigned" {
            controller = 'rest'
            action = [GET: 'getUnassignedTasks']
        }

        "/api/tasks/assigned" {
            controller = 'rest'
            action = [GET: 'getAssignedTasks']
        }

        "/api/tasks/candidate" {
            controller = 'rest'
            action = [GET: 'getAssignedTasks']
        }

        "/api/task/$id" {
            controller = 'rest'
            action = [GET: 'getTaskById']
        }

        "/api/task/claim/$id" {
            controller = 'rest'
            action = [PUT: 'claimTask']
        }

        "/api/task/unclaim/$id" {
            controller = 'rest'
            action = [PUT: 'unclaimTask']
        }

        "/api/task/save/$id" {
            controller = 'rest'
            action = [PATCH: 'unclaimTask']
        }

        "/api/task/complete/$id" {
            controller = 'rest'
            action = [PUT: 'completeTask']
        }

        "/api/task/assignee/$id" {
            controller = 'rest'
            action = [PUT: 'setAssignee']
        }

        "/api/task/priority/$id" {
            controller = 'rest'
            action = [PUT: 'setAssignee']
        }

        "/api/task/form/start/key/$id" {
            controller = 'rest'
            action = [GET: 'getStartFormKey']
        }

        "/api/task/form/task/key/$id" {
            controller = 'rest'
            action = [GET: 'getTaskFormKey']
        }

        "/api/task/form/start/data/$id" {
            controller = 'rest'
            action = [GET: 'getStartFormKey']
        }

        "/api/task/form/task/data/$id" {
            controller = 'rest'
            action = [GET: 'getTaskFormKey']
        }

        "/api/process/count/$id" {
            controller = 'rest'
            action = [GET: 'countProcessInstances']
        }

        "/api/process/tasks/$id" {
            controller = 'rest'
            action = [GET: 'getProcessInstanceTasks']
        }

        "/api/candidates/task/$id" {
            controller = 'rest'
            action = [GET: 'getCandidatesByTask']
        }

        "/api/users/sync/" {
            controller = 'rest'
            action = [PATCH: 'syncUser']
        }

	}
}
