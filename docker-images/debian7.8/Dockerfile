FROM hub.c.163.com/public/debian:7.8

MAINTAINER lifeng.wang lifeng.a.wang@intel.com

WORKDIR /root
ADD ./boot.sh /root/


RUN echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/debian/ wheezy main non-free contrib" >/etc/apt/sources.list && \
    echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/debian/ wheezy-updates main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/debian/ wheezy-backports main non-free contrib" >>/etc/apt/sources.list && \
    apt-get update && \
    apt-get install -y ssh vim ntp && \
    sed -i "s/^server/#server/g" /etc/ntp.conf && \
    echo "server 10.239.47.181 perfer" >> /etc/ntp.conf && \
    chmod a+x /root/boot.sh
#RUN sed -i "s/prohibit-password/yes/g" /etc/ssh/sshd_config && \
#    sed -i "s/PasswordAuthentication no/PasswordAuthentication yes/g" /etc/ssh/sshd_config && \
    #sed -i "s/PubkeyAuthentication yes/PubkeyAuthentication no/g" /etc/ssh/sshd_config

ADD ./authorized_keys /root/.ssh/
Add ./id_rsa /root/.ssh/
ENTRYPOINT ["/root/boot.sh"]
