class UserFilters {

    def camundaService

    def filters = {
        loginCheck(controller: '*', action: '*') {
            before = {

                //just for testing
                if (!session.user) session.user = "user-test"

                if (!session.user && !actionName.equals('login')) {
                    redirect(action: 'login')
                    return false
                }
                else {
                    session.userTasksCount = camundaService.getAssignedTasks(session.user)?.size()

                }
            }
        }
    }
}