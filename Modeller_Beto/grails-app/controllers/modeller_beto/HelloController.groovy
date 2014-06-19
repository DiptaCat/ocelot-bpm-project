package modeller_beto

class HelloController {

    def index() {
        // Cuando no se especifica se ejecuta el .gsp correspondiente en "views"
        //render "Hey Joe"
        //render (view:'jimi.gsp')

        /*def a = 'John Doe'
        a = new Object()
        a = 10
        println a
        println "Probando index"*/


    }

    def jimi() {

        //render "Jimi Hendrix"

        //"clear".execute()
        //"mkdir foo".execute()


        def firstName = 'Jimi'
        def lastName = 'Hendrix'
        def a = 27
        def b = 1970

        println "${lastName}, ${firstName}"
        println "a + b = ${a + b}"
        println '${lastName}, ${firstName}'
        println 'a + b = ${a + b}'

        // -------------
        // LISTAS GROOVY
        // -------------

        def groovyList = []
        println "groovyList: " + groovyList[0]
        groovyList.add("JIMI")
        groovyList.add "HENDRIX"
        println "groovyList: " + groovyList[0]

        // Inicializacion Lista:
        def alist = [10, 20, 30, 40, 50]

        alist << 60
        alist << 70
        alist << "JIMI"

        // Recorrer la lista:
        alist.each { number ->
            println "$number"
        }

        def alist2 = [10, 20, 30, 40, 50]
        // Cada elemento de la lista se multiplica por 10, pero saca toda la lista de golpe
        println alist2.collect{it * 10}

        println 'Size: ' + alist.size()
        println "Size rollo Scripting: ${alist.size()}"
        println "Size rollo Scripting Sin { }: $alist.size"

        // -------------------
        // DICCIONARIOS GROOVY
        // -------------------

        def emptyMap = [:]
        def mapWithValues = [grupo:'HendrixExperience',  componente:'Jimi']

        mapWithValues['Guns'] = 'Slash'
        mapWithValues['HendrixExperience'] = 'Jimi'
        mapWithValues.put('Oasis', 'Noel Gallagher')
        //mapWithValues << [grupo:'Brew']
        println mapWithValues['Guns']
        println mapWithValues['HendrixExperience']
        //println mapWithValues['Brew']
        println mapWithValues['HendrixExperience']
        println mapWithValues.'HendrixExperience'
        println mapWithValues.get('HendrixExperience')

    }
}