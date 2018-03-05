package agencias

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view: '/general/index')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
