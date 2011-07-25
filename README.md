JavaMelody Nagios plugin
=============

This plugin can be used to capture JavaMelody Data in Nagios

Installation
-----------

Before installation you will need to have java 1.5 or higher.

```
wget https://github.com/downloads/sbower/nagios_javamelody_plugin/javamelody_plugin.tar.gz
tar xzvf javamelody_plugin.tar.gz
mv javamelody_plugin/* /usr/local/nagios/libexec
```

Usage
-----

###  Commands

First define a command in /etc/nagios/commands.cfg.

#### Local

Setting a command to check local heap

$ARG1$ -- warning level <br />
$ARG2$ -- critical level <br />
$ARG3$ -- war name (Verify this in your tomcat temp folder) <br />

```
# 'check_local_heap' command definition
define command{
        command_name    check_heap
        command_line    $USER1$/check_jmelody -r /usr/local/tomcat/temp/javamelody/$ARG3$/ -uh -w $ARG1$ -c $ARG2$
        }
```
        
#### Remote

```
# 'check_remote_heap' command definition
define command{
        command_name    check_remote_heap
        command_line    $USER1$/check_by_ssh -H $HOSTADDRESS$ -C '/usr/local/nagios/libexec/check_jmelody -r /usr/local/tomcat/temp/javamelody/$ARG3$/ -uh -w $ARG2$ -c $ARG3$'
        }
```

### Service

Then you will need to define a service in your host definition

```
# Heap
define service{
        use                             remote-service,graphed-service         ; Name of service template to use
        host_name                       hostname 
        service_description             Heap
        check_command                   check_heap!6442450944!8589934592!warfile
        }
```

Testing
-------

To run the tests:

    $ mvn test


Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b nagios_javamelody_plugin`)
3. Commit your changes (`git commit -am "Added Snarkdown"`)
4. Push to the branch (`git push origin nagios_javamelody_plugin`)
5. Create an [Issue][1] with a link to your branch
6. Enjoy a refreshing Diet Coke and wait


[1]: https://github.com/sbower/nagios_javamelody_plugin/issues