modules = {
    application {
        resource url: 'js/application.js'
        resource url: 'css/bootstrap.css'
        resource url: 'css/bootstrap_custom.css'
        resource url: 'js/ie9/html5shiv.js', disposition: 'head', wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
        resource url: 'js/ie9/respond.js', disposition: 'head', wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
    }
    codemirror {
        resource url: 'js/codemirror-4.0/codemirror.js'
        resource url: 'js/codemirror-4.0/mode/groovy/groovy.js'
        resource url: 'css/codemirror.css'
        resource url: 'css/codemirror_override.css'
    }
}