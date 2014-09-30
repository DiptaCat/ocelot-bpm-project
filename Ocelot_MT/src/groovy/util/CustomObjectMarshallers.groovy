package util

/**
 * Created by sergi on 18/08/14.
 */
class CustomObjectMarshallers {

    def marshallers = []

    def register(){
        marshallers.each {
            it.register()
        }
    }
}
