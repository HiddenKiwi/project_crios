package register

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ANONYMOUS"])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    // Nothing to override, just created to be reachable for anonymous users
}
