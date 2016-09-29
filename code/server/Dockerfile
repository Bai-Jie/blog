FROM openjdk:jre

ENV PROGRAME_NAME=server
ENV PROGRAME_VERSION=0.1.0
ENV PROGRAME=${PROGRAME_NAME}-${PROGRAME_VERSION}

ADD https://dl.bintray.com/baijie/generic/gq/baijie/blog/server/nightly/build/distributions/$PROGRAME.tar /opt/
RUN cd /opt && tar -xf $PROGRAME.tar
WORKDIR /opt/$PROGRAME
ENTRYPOINT bin/${PROGRAME_NAME}
EXPOSE 80
