#!/bin/sh

httpd
/usr/sbin/sshd -D -e "$@"