modules = {
    application {
        resource url: 'js/ie9/respond.js', disposition: 'head', wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
        resource url: 'js/ie9/html5shiv.js', disposition: 'head', wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
        resource url: 'js/jquery/jquery-1.10.2.js'
        resource url: 'css/bootstrap.css'
        resource url: 'js/bootstrap/bootstrap.js'
        resource url: 'css/bootstrap_custom.css'
        resource url: 'js/application.js'
    }
    codemirror {
        resource url: 'js/codemirror-5.6/codemirror.js'
        resource url: 'js/codemirror-5.6/mode/groovy/groovy.js'
        resource url: 'css/codemirror.css'
        resource url: 'css/codemirror_override.css'
    }
}