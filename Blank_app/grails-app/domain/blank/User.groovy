package blank

class User {

    String name
    String login
    Date dateCreated

    static hasMany = [bpms: Bpm, favouriteBPMs:Bpm]
    static mappedBy = [ favouriteBPMs: "none" ]



    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        name(blank:false, minSize:2)
        login(blank:false, unique:true, minSize:2)
        favouriteBPMs nullable: true
    }

    def getFavourites(){
        return this.favouriteBPMs//.sort{it.lastUpdated}
    }

    def existBpm(long id) {
        favouriteBPMs.contains(Bpm.findById(id))
    }

    String toString() {
        "${login}"
    }
}