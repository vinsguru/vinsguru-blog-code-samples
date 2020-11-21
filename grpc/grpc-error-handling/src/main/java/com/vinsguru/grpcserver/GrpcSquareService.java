package com.vinsguru.grpcserver;

import com.vinsguru.calculator.*;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;


public class GrpcSquareService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void findSquare(Request request, StreamObserver<Response> responseObserver) {
        int number = request.getNumber();

        Response.Builder builder = Response.newBuilder();
        if(number < 2 || number > 20){
            ErrorCode errorCode = number > 20 ? ErrorCode.ABOVE_20 : ErrorCode.BELOW_2;
            ErrorResponse errorResponse = ErrorResponse.newBuilder()
                    .setInput(number)
                    .setErrorCode(errorCode)
                    .build();
            builder.setErrorResponse(errorResponse);
        }else{
            // only valid ranges
            builder.setSuccessResponse(SuccessResponse.newBuilder().setResult(number * number).build());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

}
