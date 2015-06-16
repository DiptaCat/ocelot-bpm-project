import ocelot.Member

class UserFilters {

	def filters = {
		all(controller: '*', action: '*') {
			before = {
				if (!session.user) {
					session.user = Member.list().first()
					println "user @${session.user.name} logged"
				}
			}
		}
	}
}
