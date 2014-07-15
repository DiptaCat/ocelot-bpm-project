package blank

class User {

    String name
    String login
    Date dateCreated

    static hasMany = [bpms: Bpm, favouriteBPMs:Bpm]
    static mappedBy = [favouriteBPMs: 'none']


    static mapping = {
        autoTimestamp true
        favouriteBPMs joinTable: false
        /*favouriteBPMs cascade: 'all-delete-orphan'
        bpms cascade: 'all-delete-orphan'*/
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
}