package com.youngzy.book.javaee8cookbook.ch01;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import static com.youngzy.book.javaee8cookbook.ch01.ServerMock.BASE_PATH;

/**
 * The Client
 *
 * @author youngzy
 * @since 2024-01-02
 */
public class ClientConsumer {
    public static final Client CLIENT = ClientBuilder.newClient();
    public static final WebTarget WEB_TARGET = CLIENT.target(ServerMock.CONTEXT + BASE_PATH);

    public static void main(String[] args) {
        try (final SseEventSource sseSource =
                     SseEventSource
                             .target(WEB_TARGET)
                             .build()) {

            sseSource.register(System.out::println);
            sseSource.open();

            for (int counter=0; counter < 5; counter++) {
                System.out.println(" ");
                for (int innerCounter=0; innerCounter < 5; innerCounter++) {
                    WEB_TARGET.request().post(Entity.json("event " + innerCounter));
                }
                Thread.sleep(1000);
            }

            CLIENT.close();
            System.out.println("\nAll messages consumed");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
