class UserController {

    def camundaService

    def index() {
        [user: session.user, group: session.group]
    }

    def change() {

        session.user = params.user
        session.group = params.group

        camundaService.syncUser(session.user, [session.group])

        redirect(controller:'task', action:'index')
    }





}
