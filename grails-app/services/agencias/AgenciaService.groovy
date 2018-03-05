package agencias
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class AgenciaService {

    def serviceMethod() {}

    // Trae los sitios de MercadoLibre disponibles
    def traerSitios() {

        def urlSitios = new URL('https://api.mercadolibre.com/sites')
        def conexionSitios = (HttpURLConnection) urlSitios.openConnection()

        conexionSitios.setRequestMethod("GET")
        conexionSitios.setRequestProperty("Accept", "application/json")
        conexionSitios.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper jsonSitios = new JsonSlurper()

        return jsonSitios.parse(conexionSitios.getInputStream())
    }

    // Obtiene los metodos de pago disponibles para un sitio en particular
    def traerMetodosDePago(sitio) {

        def urlMetodos = new URL('https://api.mercadolibre.com/sites/' + sitio + '/payment_methods')
        def conexionMetodos = (HttpURLConnection) urlMetodos.openConnection()

        conexionMetodos.setRequestMethod("GET")
        conexionMetodos.setRequestProperty("Accept", "application/json")
        conexionMetodos.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper jsonMetodos = new JsonSlurper()

        return jsonMetodos.parse(conexionMetodos.getInputStream()).findAll { map -> map.payment_type_id == "ticket" }
    }

    // Obtiene las agencias disponibles para una direccion, un radio y un metodo seleccionados
    def traerAgencias(direccion, radio, metodo) {

        //## SACAR LA KEY DE ACA
        def key = "AIzaSyA1NUB2z_ql-EjLvXHsj0J1aDfzhijFJVU"
        def urlDireccion = new URL('https://maps.googleapis.com/maps/api/geocode/json?address=' + URLEncoder.encode(direccion, "UTF-8") + '&key=' + key)
        def conexionDireccion = (HttpURLConnection) urlDireccion.openConnection()

        conexionDireccion.setRequestMethod("GET")
        conexionDireccion.setRequestProperty("Accept", "application/json")
        conexionDireccion.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper jsonDireccion = new JsonSlurper()

        def location = jsonDireccion.parse(conexionDireccion.getInputStream())
        def latitud = location.results.geometry.location.lat[0]
        def longitud = location.results.geometry.location.lng[0]
        def urlAgencias = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods/' + metodo + '/agencies' + '?near_to=' + latitud + ',' + longitud + ',' + radio)
        def conexionAgencias = (HttpURLConnection) urlAgencias.openConnection()

        conexionAgencias.setRequestMethod("GET")
        conexionAgencias.setRequestProperty("Accept", "application/json")
        conexionAgencias.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper jsonAgencias = new JsonSlurper()
        //## Hacen falta pasar la latitud y longitud?? Sacarlas!
        def agenciasJson = jsonAgencias.parse(conexionAgencias.getInputStream()).results
        return [direccion: direccion, latitud: latitud, longitud: longitud, agencias: jsonAListaDeAgencias(agenciasJson)]
    }

    // Parsea un Json de agencias y devuelve una lista de entidades Agencia.
    // Util para trabajar mas limpio desde las vistas y unicamente con los atributos necesarios.
    // Ademas divide la location en latitud y longitud y evita hacer el split desde javascript.
    def jsonAListaDeAgencias(jsonAgencias) {

        def agencias = []
        def addr
        def location

        for (i in jsonAgencias) {
            addr = i.address
            location = addr.location.split(",")
            addr = i.address
            Agencia agencia = new Agencia(descripcion: i.description, distancia: i.distance,
                    direccion: addr.address_line, ciudad: addr.city, estado: addr.state,
                    pais: addr.country, latitud: location[0], longitud: location[1])
            agencias << agencia
        }
        return agencias
    }
}