FROM httpd:alpine

RUN apk add --update --no-cache openssh \
    && echo 'PasswordAuthentication yes' >> /etc/ssh/sshd_config \
    && ssh-keygen -A \
    && adduser -h /home/deployer -s /bin/sh -D deployer \
    && echo -n 'deployer:deployer' | chpasswd \
    && adduser deployer www-data \
    && chown -R www-data: /usr/local/apache2/htdocs/ \
    && chmod uag+w /usr/local/apache2/htdocs/

COPY /apache/entrypoint.sh /entrypoint.sh
COPY /apache/httpd.conf /usr/local/apache2/conf/httpd.conf

ENTRYPOINT ["/entrypoint.sh"]