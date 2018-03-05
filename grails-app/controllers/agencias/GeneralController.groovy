package agencias

class GeneralController {

    // Instancia de Servicio
    def agenciaService

    def index() {
        try {
            [sitios: agenciaService.traerSitios()]
        } catch (IOException) {
            def mensaje = "Error en el servidor!"
            render(view: "error", model: [mensaje: mensaje])
        }
    }

    def buscarMetodosDePago() {
        def sitio = params.sitio
        def mensaje
        if (!sitio) {
            mensaje = "Sitio Invalido!"
            render(view: "error", model: [mensaje: mensaje])
        }
        else {
            try {
                [metodos: agenciaService.traerMetodosDePago(sitio)]
            } catch (IOException) {
                mensaje = "Error en el servidor!"
                render(view: "error", model: [mensaje: mensaje])
            }
        }
    }

    def buscarAgencias() {

        def direccion = params.direccion
        def radio
        try {
            radio = Integer.parseInt(params.radio)
        } catch (NumberFormatException) {
            radio = 0
        }
        def metodo = params.metodo
        def mensaje
        if (!direccion || !radio || radio <= 0) {
            // Informa sobre el error al usuario!
            mensaje = "Direccion o Radio Invalidos!"
            render(view: "error", model: [mensaje: mensaje])
        } else if (!metodo) {
            mensaje = "No hay agencias disponibles con ese metodo de pago!"
            render(view: "error", model: [mensaje: mensaje])
        }
        else {
            try {
                [modelo: agenciaService.traerAgencias(direccion, radio, metodo)]
            } catch (IOException) {
                mensaje = "Error en el servidor!"
                render(view: "error", model: [mensaje: mensaje])
            }
        }
    }

}