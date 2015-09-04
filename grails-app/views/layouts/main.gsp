<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Groovy Koans"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <r:require modules="application"/>
    <g:layoutHead/>
    <ga:trackPageview />
    <r:layoutResources/>
</head>

<body>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <g:link controller="koan" class="navbar-brand">Groovy Koans</g:link>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li>
                    <g:link content="koan">Introduction</g:link>
                </li>
                <li>
                    <g:link action="view" params="[number: 0]">Start</g:link>
                </li>
                <li>
                    <g:link controller="koan" action="list">Overview</g:link>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Resources <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" aria-labelledby="themes">
                         <g:each in="${chapters}" var="chapter">
                            <g:each in="${chapter.links}" var="link">
                                <li><a href="${link.url}"
                                       target="_blank">${link.name}</a></li>
                            </g:each>
                        </g:each>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><g:link target="_blank" style="padding: 5px; border: none;" url="http://jdriven.com"><g:img
                        height="40" dir="images" file="jdriven.png"/></g:link></li>
            </ul>

        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1 ">
            <g:layoutBody/>
        </div>
    </div>
</div>
<r:layoutResources/>
</body>
</html>
