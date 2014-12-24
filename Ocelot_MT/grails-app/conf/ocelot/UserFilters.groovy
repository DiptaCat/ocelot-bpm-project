package ocelot

class UserFilters {

	def filters = {
		all(controller:'*', action:'*') {
			before = {
               session.user = Member.get(1)
			}
			after = { Map model ->

			}
			afterView = { Exception e ->

			}
		}
	}
}
