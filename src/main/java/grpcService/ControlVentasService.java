package grpcService;

import delivery.*;
import grpc.ControlDeVentasGrpc;
import grpc.ControlVentas;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Random;

import static delivery.Producto.generaProductos;


public class ControlVentasService extends ControlDeVentasGrpc.ControlDeVentasImplBase {
    @Override
    public StreamObserver<ControlVentas.VentaGRPC> registrarVenta(StreamObserver<ControlVentas.BeneficioGRPC> responseObserver) {
        return new StreamObserver<ControlVentas.VentaGRPC>(){
            double PrecioTotal = 0.0;
            double beneficio = 0.0;
            //OnNext: este método se ejecuta cada vez que recibe un mensaje del cliente.
            @Override
            public void onNext(ControlVentas.VentaGRPC ventaGRPC) {
                PrecioTotal += (ventaGRPC.getPrecio()*ventaGRPC.getCantidad());
            }

            //OnCompleted: se ejecuta cuando el stream de mensajes del cliente termina, y le envia su respuesta.
            @Override
            public void onCompleted() {
                beneficio = PrecioTotal * 0.25;

                ControlVentas.BeneficioGRPC beneficioGRPC = ControlVentas.BeneficioGRPC.newBuilder()
                        .setTotal(beneficio).build();

                responseObserver.onNext(beneficioGRPC);
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                System.out.print("error:{}"+ t.getMessage());
            }
        };
    }

    @Override
    public StreamObserver<ControlVentas.RestauranteGRPC> ventas(StreamObserver<ControlVentas.ProductoGRPC> responseObserver) {
        return new StreamObserver<ControlVentas.RestauranteGRPC>(){
            StringBuffer buffer = new StringBuffer();
            Random r = new Random();
            int numProductos;

            @Override
            public void onNext(ControlVentas.RestauranteGRPC value) {
                buffer.append(value.getIdRestaurante()).append(" , ");

                numProductos = 1+r.nextInt (4);

                List<Producto> lp = generaProductos(numProductos);

                for(Producto p: lp){
                    ControlVentas.ProductoGRPC producto = ControlVentas.ProductoGRPC.newBuilder()
                            .setIdProducto(p.getId())
                            .setCantidad(p.getCantidad())
                            .build();
                    responseObserver.onNext(producto);
                }

            }
            //OnCompleted: se ejecuta cuando el stream de mensajes del cliente termina, y le envia su respuesta.
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                System.out.print("error:{}"+ t.getMessage());
            }

        };
    }
}
