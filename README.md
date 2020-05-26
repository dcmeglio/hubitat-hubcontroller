# hubitat-hubcontroller
 This driver provides access to common hub commands to reboot the hub, restart the Hubitat application, and shutdown the hub.

## Drivers
Install the __Hub Controler__ driver and create a Virtual Device. 

### Configuration
The app supports both hubs that use Hub Security and those that do not. If you are using hub security you will need to supply the username and password of an administrative user for your hub. Optionally you can also specify an IP address. If specified this will allow you to perform the commands on a different (remote) hub. Use 127.0.0.1 to refer to the local hub.

Three custom commands are provided, `restart`, `reboot`,  and `shutdown`.

## Donations
If you find this driver useful, please consider making a [donation](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=7LBRPJRLJSDDN&source=url)! 
