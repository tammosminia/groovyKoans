<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>

    %{--ShareThis--}%
    <script type="text/javascript">var switchTo5x=true;</script>
    <script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
    <script type="text/javascript">stLight.options({publisher: "0fec31ed-bb9b-45d6-a0d5-4a029041e0ac", doNotHash: true, doNotCopy: true, hashAddressBar: false});</script>
</head>

<body>

<p>That were all the tests. You're a groovy master now!</p>

Next:
<ul>
    <li>Share your success.<br/>
        <div id="fb-root"></div>
        <script>
            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) return;
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/nl_NL/sdk.js#xfbml=1&version=v2.4";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <span class="fb-share-button" data-href="http://www.groovy-koans.org/" data-layout="button"></span>
        %{--<span class='st_facebook_large' displayText='Facebook'></span>--}%
        <span class='st_twitter_large' displayText='Tweet'></span>
        <span class='st_linkedin_large' displayText='LinkedIn'></span>
        <span class='st_googleplus_large' displayText='Google +'></span>
        <span class='st_pinterest_large' displayText='Pinterest'></span>
    </li>
    <li><a href="http://groovy.codehaus.org/Download?nc">Download groovy</a></li>
    <li><a href="https://grails.org/">Learn grails</a></li>
</ul>
</body>
</html>