package ocelot.xe

class UserController {

    def workflowService

    def index() {
        [user: session.user, group: session.group]
    }

    def change() {

        session.user = params.user
        session.group = params.group

        workflowService.syncUser(session.user, [session.group])

        redirect(controller:'task', action:'index')
    }





}
