package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.ManagedChannelBuilder;
import java.time.Duration;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.acme.HelloGrpcGrpc.HelloGrpcBlockingStub;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class HelloGrpcServiceTest {

    @GrpcClient
    HelloGrpc helloGrpc;

    @Test
    public void testHello() {
        HelloReply helloReply = helloGrpc
                .sayHello(HelloRequest.newBuilder().setName("Neo").build()).await()
                .atMost(Duration.of(60, ChronoUnit.SECONDS));
        assertEquals("Hello Neo!", helloReply.getMessage());
    }

}
