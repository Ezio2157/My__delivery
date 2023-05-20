package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: control_ventas.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ControlDeVentasGrpc {

  private ControlDeVentasGrpc() {}

  public static final String SERVICE_NAME = "ControlDeVentas";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.ControlVentas.VentaGRPC,
      grpc.ControlVentas.BeneficioGRPC> getRegistrarVentaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registrarVenta",
      requestType = grpc.ControlVentas.VentaGRPC.class,
      responseType = grpc.ControlVentas.BeneficioGRPC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.ControlVentas.VentaGRPC,
      grpc.ControlVentas.BeneficioGRPC> getRegistrarVentaMethod() {
    io.grpc.MethodDescriptor<grpc.ControlVentas.VentaGRPC, grpc.ControlVentas.BeneficioGRPC> getRegistrarVentaMethod;
    if ((getRegistrarVentaMethod = ControlDeVentasGrpc.getRegistrarVentaMethod) == null) {
      synchronized (ControlDeVentasGrpc.class) {
        if ((getRegistrarVentaMethod = ControlDeVentasGrpc.getRegistrarVentaMethod) == null) {
          ControlDeVentasGrpc.getRegistrarVentaMethod = getRegistrarVentaMethod =
              io.grpc.MethodDescriptor.<grpc.ControlVentas.VentaGRPC, grpc.ControlVentas.BeneficioGRPC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registrarVenta"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ControlVentas.VentaGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ControlVentas.BeneficioGRPC.getDefaultInstance()))
              .setSchemaDescriptor(new ControlDeVentasMethodDescriptorSupplier("registrarVenta"))
              .build();
        }
      }
    }
    return getRegistrarVentaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.ControlVentas.RestauranteGRPC,
      grpc.ControlVentas.ProductoGRPC> getVentasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ventas",
      requestType = grpc.ControlVentas.RestauranteGRPC.class,
      responseType = grpc.ControlVentas.ProductoGRPC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.ControlVentas.RestauranteGRPC,
      grpc.ControlVentas.ProductoGRPC> getVentasMethod() {
    io.grpc.MethodDescriptor<grpc.ControlVentas.RestauranteGRPC, grpc.ControlVentas.ProductoGRPC> getVentasMethod;
    if ((getVentasMethod = ControlDeVentasGrpc.getVentasMethod) == null) {
      synchronized (ControlDeVentasGrpc.class) {
        if ((getVentasMethod = ControlDeVentasGrpc.getVentasMethod) == null) {
          ControlDeVentasGrpc.getVentasMethod = getVentasMethod =
              io.grpc.MethodDescriptor.<grpc.ControlVentas.RestauranteGRPC, grpc.ControlVentas.ProductoGRPC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ventas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ControlVentas.RestauranteGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.ControlVentas.ProductoGRPC.getDefaultInstance()))
              .setSchemaDescriptor(new ControlDeVentasMethodDescriptorSupplier("ventas"))
              .build();
        }
      }
    }
    return getVentasMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ControlDeVentasStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasStub>() {
        @java.lang.Override
        public ControlDeVentasStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeVentasStub(channel, callOptions);
        }
      };
    return ControlDeVentasStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ControlDeVentasBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasBlockingStub>() {
        @java.lang.Override
        public ControlDeVentasBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeVentasBlockingStub(channel, callOptions);
        }
      };
    return ControlDeVentasBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ControlDeVentasFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ControlDeVentasFutureStub>() {
        @java.lang.Override
        public ControlDeVentasFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ControlDeVentasFutureStub(channel, callOptions);
        }
      };
    return ControlDeVentasFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.ControlVentas.VentaGRPC> registrarVenta(
        io.grpc.stub.StreamObserver<grpc.ControlVentas.BeneficioGRPC> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRegistrarVentaMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<grpc.ControlVentas.RestauranteGRPC> ventas(
        io.grpc.stub.StreamObserver<grpc.ControlVentas.ProductoGRPC> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getVentasMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ControlDeVentas.
   */
  public static abstract class ControlDeVentasImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ControlDeVentasGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ControlDeVentas.
   */
  public static final class ControlDeVentasStub
      extends io.grpc.stub.AbstractAsyncStub<ControlDeVentasStub> {
    private ControlDeVentasStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeVentasStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeVentasStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.ControlVentas.VentaGRPC> registrarVenta(
        io.grpc.stub.StreamObserver<grpc.ControlVentas.BeneficioGRPC> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getRegistrarVentaMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.ControlVentas.RestauranteGRPC> ventas(
        io.grpc.stub.StreamObserver<grpc.ControlVentas.ProductoGRPC> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getVentasMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ControlDeVentas.
   */
  public static final class ControlDeVentasBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ControlDeVentasBlockingStub> {
    private ControlDeVentasBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeVentasBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeVentasBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ControlDeVentas.
   */
  public static final class ControlDeVentasFutureStub
      extends io.grpc.stub.AbstractFutureStub<ControlDeVentasFutureStub> {
    private ControlDeVentasFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControlDeVentasFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ControlDeVentasFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REGISTRAR_VENTA = 0;
  private static final int METHODID_VENTAS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTRAR_VENTA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.registrarVenta(
              (io.grpc.stub.StreamObserver<grpc.ControlVentas.BeneficioGRPC>) responseObserver);
        case METHODID_VENTAS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.ventas(
              (io.grpc.stub.StreamObserver<grpc.ControlVentas.ProductoGRPC>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getRegistrarVentaMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              grpc.ControlVentas.VentaGRPC,
              grpc.ControlVentas.BeneficioGRPC>(
                service, METHODID_REGISTRAR_VENTA)))
        .addMethod(
          getVentasMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              grpc.ControlVentas.RestauranteGRPC,
              grpc.ControlVentas.ProductoGRPC>(
                service, METHODID_VENTAS)))
        .build();
  }

  private static abstract class ControlDeVentasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ControlDeVentasBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.ControlVentas.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ControlDeVentas");
    }
  }

  private static final class ControlDeVentasFileDescriptorSupplier
      extends ControlDeVentasBaseDescriptorSupplier {
    ControlDeVentasFileDescriptorSupplier() {}
  }

  private static final class ControlDeVentasMethodDescriptorSupplier
      extends ControlDeVentasBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ControlDeVentasMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ControlDeVentasGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ControlDeVentasFileDescriptorSupplier())
              .addMethod(getRegistrarVentaMethod())
              .addMethod(getVentasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
