package ocelot.xe

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class ModelerService {

    def grailsApplication

    def obtainAll() {

        def rest = new RestBuilder()

        def url = grailsApplication.config.ocelot.mt.rest.list

        def resp = rest.get(url)

        println "$url -> ${resp.json}"

        resp.json
    }

    def obtain(modelId) {

         def rest = new RestBuilder()

        def url = grailsApplication.config.ocelot.mt.rest.export + "/" + modelId

        def resp = rest.get(url)

        println "$url -> ${resp.json}"

        resp.json
    }
}
