#ubuntu14.04 for cdh
#FROM 10.239.47.144:5000/ubuntu:14.04
FROM ubuntu:1404

MAINTAINER kai.a.xu kai.a.xu@intel.com

WORKDIR /opt
RUN mkdir -p /root/.ssh
ADD ./ssh/* /root/.ssh/
ADD ./boot.sh /opt/boot.sh
RUN chmod +x /opt/boot.sh
ADD ./cdh.list /etc/apt/sources.list.d/

RUN echo "search sh.intel.com" > /etc/resolv.conf && \
    echo "nameserver 10.248.2.5" >> /etc/resolv.conf && \
    echo "nameserver 10.239.27.228" >> /etc/resolv.conf && \
    echo "nameserver 172.17.6.9" >> /etc/resolv.conf

RUN export http_proxy=http://child-prc.intel.com:913 && \
    export https_proxy=https://child-prc.intel.com:913 && \
    wget http://archive.cloudera.com/cm5/installer/latest/cloudera-manager-installer.bin && \
    chmod +x cloudera-manager-installer.bin

RUN echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty main restricted universe multiverse" > /etc/apt/sources.list && \
    echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-security main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-updates main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-backports main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-security main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-updates main restricted universe multiverse" >> /etc/apt/sources.list && \
    echo "deb-src http://linux-ftp.sh.intel.com/pub/mirrors/ubuntu/ trusty-backports main restricted universe multiverse" >> /etc/apt/sources.list

RUN apt-get update && \
    apt install -y sudo ntp psmisc apt-utils vim expect libmysql-java && \
    sed -i "s/^server/#server/g" /etc/ntp.conf && \
    echo "server bdpa-gateway.sh.intel.com iburst" >> /etc/ntp.conf
    #service ntp restart
RUN sed -i "s/prohibit-password/yes/g" /etc/ssh/sshd_config && \
    sed -i "s/PasswordAuthentication no/PasswordAuthentication yes/g" /etc/ssh/sshd_config && \
    sed -i "s/PermitRootLogin without-password/PermitRootLogin yes/g" /etc/ssh/sshd_config
    #sed -i "s/PubkeyAuthentication yes/PubkeyAuthentication no/g" /etc/ssh/sshd_config

RUN echo "#CM & CDH repos"   > /etc/apt/sources.list.d/cdh.list && \
    echo "deb [arch=amd64] http://cts-gateway.sh.intel.com/repos/5.14-RC1/cdh/ubuntu_trusty/amd64/cdh/  trusty-cdh5.14.0 contrib"   >> /etc/apt/sources.list.d/cdh.list && \
    echo "deb-src   http://cts-gateway.sh.intel.com/repos/5.14-RC1/cdh/ubuntu_trusty/amd64/cdh/  trusty-cdh5.14.0 contrib"   >> /etc/apt/sources.list.d/cdh.list && \
    echo "deb [arch=amd64]   http://cts-gateway.sh.intel.com/repos/5.14-RC1/cm/ubuntu_trusty/amd64/cm/ trusty-cm5.14.0 contrib"   >> /etc/apt/sources.list.d/cdh.list && \
    echo "deb-src   http://cts-gateway.sh.intel.com/repos/5.14-RC1/cm/ubuntu_trusty/amd64/cm/  trusty-cm5.14.0 contrib"   >> /etc/apt/sources.list.d/cdh.list && \
    wget -q   http://cts-gateway.sh.intel.com/repos/5.14-RC1/cm/ubuntu_trusty/amd64/cm/archive.key  -O- | sudo apt-key add - && \
    apt-get update
    

ENTRYPOINT ["/opt/boot.sh"]    





