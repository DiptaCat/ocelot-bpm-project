package blank

class User {

    String name
    String login
    Date dateCreated

    static hasMany = [bpms: Bpm]
    def favouriteBPMs = []


    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        name(blank:false, minSize:2)
        login(blank:false, unique:true, minSize:2)
    }

    def addBPMToFavourites(Bpm bpm) {
        this.favouriteBPMs.add(bpm)
    }

    def getFavourites(){
        return this.favouriteBPMs.sort{it.lastUpdated}
    }
}
