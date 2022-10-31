FROM openjdk:11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY ./target/dataset-1.0-SNAPSHOT.jar ./app.jar

EXPOSE 8080
COPY docker-entrypoint.sh .
RUN chown nobody ./docker-entrypoint.sh && chmod u+x ./docker-entrypoint.sh
RUN useradd -ms /bin/bash datasetappuser
USER datasetappuser
CMD ["./docker-entrypoint.sh"]