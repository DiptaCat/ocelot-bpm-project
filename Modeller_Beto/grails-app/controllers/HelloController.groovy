

class HelloController {

    def artistList = []

    def index() {
        // Cuando no se especifica se ejecuta el .gsp correspondiente en "views"
        //render "Hey Joe"
        //render (view:'jimi.gsp')

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

        // -------------------
        // BUCLES GROOVY
        // -------------------

        // Realitzar 5 iteraciones
        5.times {
            println "5 iteraciones"
        }

        // Recorrer lista

        def alist3 = [11, 22, 33, 44, 55]

        // Recorrer elementos
        alist3.each { name ->
            println name
        }

        // Recorrer ciertos elementos: output: 3 4 5
        (3..5).each { number ->
            println number
        }

        render (view:'jimi.gsp')

    }

    def jimi() {


    }

    def guardar(String textNom, String textGrup) {

//        render "Ejecutando Accion Guardar"
        println "guardar: Guardando artista..."

        Artista artista = new Artista ()

        artista.setNom(textNom)
        artista.setGrup(textGrup)
        artista.say("Hey man!")

        artistList.add(artista)

        println "guardar: Artista Guardado!"

        // Volvemos al formulario de añadir artistas
        render (view:'jimi.gsp')

    }

    def llistar() {

        //def num = 0

        println "llistar: Mostrar llista"
        render "Llista Artistes"
        render "num:nom:grup"
        render "------------------------"

        /*artistList.each { artista ->
            num++
            println "$num : ${artista.getNom()} :  ${artista.getGrup()}"
            render "$num : ${artista.getNom()} :  ${artista.getGrup()}"
        }*/


        // Recorrer lista usando indice propio del bucle each
        artistList.eachWithIndex { artista, i ->
            //num++
            println "$i : ${artista.getNom()} :  ${artista.getGrup()}"
            render "$i : ${artista.getNom()} :  ${artista.getGrup()}"
        }

    }


}