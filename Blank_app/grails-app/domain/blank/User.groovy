package blank
import grails.converters.*

class User {

    String name
    String login
    Date dateCreated

    static hasMany = [bpms: Bpm, favouriteBPMs:Bpm]
    static mappedBy = [favouriteBPMs: "none"]


    static mapping = {
        autoTimestamp true
        favouriteBPMs joinTable: [name: 'FavBpm', column: 'BpmId', key: 'UserId']
    }

    static constraints = {
        name blank:false, minSize:2
        login blank:false, unique:true, minSize:2
        favouriteBPMs nullable: true
    }

    def getFavourites(){
        return this.favouriteBPMs
    }

    String toString() {
        "${login}"
    }

    def userAsJSON = {
        return this as JSON
    }

    def userAsXML = {
        return this as XML
    }
}