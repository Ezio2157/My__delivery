package grpcService;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;

public class ControlVentasServer {
    public static void main(String args[]) throws IOException, InterruptedException {

        System.out.println("\nstarting GRPC Server");

        Server server = Grpc.newServerBuilderForPort(9090, InsecureServerCredentials.create())
                .addService(new ControlVentasService())
                .build();

        server.start();

        System.out.println("ControlVentasService started at "+ server.getPort());

        server.awaitTermination();
    }
}
