/**
 *  Hubitat Hub Controller
 *
 *  Copyright 2020 Dominick Meglio
 *
 */
 
metadata {
    definition (
		name: "Hubitat Hub Controller", 
		namespace: "dcm", 
		author: "dmeglio@gmail.com",
		importUrl: "https://raw.githubusercontent.com/dcmeglio/hubitat-hubcontroller/master/drivers/Hub_Controller.groovy"
	) {
		capability "Actuator"
        
		command "reboot"
		command "restart"
		command "shutdown"
    }
}


preferences {
	input "heIP", "string", title: "Hub IP Address", defaultValue: "127.0.0.1"
    input "heSecurity", "bool", title: "Hub security enabled?"
	input "heUsername", "string", title: "Hub security username"
	input "hePassword", "password", title: "Hub security password"
}

def reboot() {
	callHubEndpoint("/hub/reboot")
}

def restart() {
	callHubEndpoint("/hub/restart")
}

def shutdown() {
	callHubEndpoint("/hub/shutdown")
}

def getURL() {
	if (heIP == "127.0.0.1")
		return "127.0.0.1:8080"
	return heIP
}

def getLoginCookie() {
	def cookie = ""

    if (heSecurity)
    {
		httpPost(
			[
				uri: "http://${getURL()}",
				path: "/login",
				query: 
				[
					loginRedirect: "/"
				],
				body:
				[
					username: heUsername,
					password: hePassword,
					submit: "Login"
				]
			]
		)
		{ resp ->
            cookie = resp?.headers?.'Set-Cookie'?.split(';')?.getAt(0)
        }
	}
	return cookie
}

def callHubEndpoint(endpoint) {
	def cookie = getLoginCookie()
	httpPost(
		[
			uri: "http://${getURL()}",
			path: endpoint,
			headers:
			[
				"Cookie": cookie
			]
		]
	) 
	{
		resp -> 
	}
}