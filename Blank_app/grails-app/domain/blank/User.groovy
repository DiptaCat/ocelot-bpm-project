package blank

class User {

    String name
    String login
    Date dateCreated

    static hasMany = [bpms: Bpm]
    List<Bpm> favouriteBPMs = [].withLazyDefault { new Bpm() }


    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        name(blank:false, minSize:2)
        login(blank:false, unique:true, minSize:2)
    }

    def getFavourites(){
        return this.favouriteBPMs.sort{it.lastUpdated}
    }

    def existBpm(long id) {
        favouriteBPMs.contains(Bpm.findById(id))
    }

    String toString() {
        "${login}"
    }
}
