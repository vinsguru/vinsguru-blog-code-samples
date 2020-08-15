package com.vinsguru.grpc.test;

import com.google.protobuf.ByteString;
import com.vinsguru.io.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadTest {

    private ManagedChannel channel;
    private FileServiceGrpc.FileServiceStub fileServiceStub;

    @Before
    public void setup(){
        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        this.fileServiceStub = FileServiceGrpc.newStub(channel);
    }

    @Test
    public void unaryServiceTest() throws InterruptedException, IOException {

        StreamObserver<FileUploadRequest> streamObserver = this.fileServiceStub.upload(new FileUploadObserver());

        // input file for testing
        Path path = Paths.get("src/test/resources/input/java_input.pdf");

        // build metadata
        FileUploadRequest metadata = FileUploadRequest.newBuilder()
                .setMetadata(MetaData.newBuilder()
                        .setName("output")
                        .setType("pdf").build())
                .build();
        streamObserver.onNext(metadata);

        // upload bytes
        InputStream inputStream = Files.newInputStream(path);
        byte[] bytes = new byte[4096];
        int size;
        while ((size = inputStream.read(bytes)) > 0){
            FileUploadRequest uploadRequest = FileUploadRequest.newBuilder()
                    .setFile(File.newBuilder().setContent(ByteString.copyFrom(bytes, 0 , size)).build())
                    .build();
            streamObserver.onNext(uploadRequest);
        }
        // close the stream
        inputStream.close();
        streamObserver.onCompleted();
    }

    @After
    public void teardown(){
        this.channel.shutdown();
    }

    private static class FileUploadObserver implements StreamObserver<FileUploadResponse> {
        @Override
        public void onNext(FileUploadResponse fileUploadResponse) {
            System.out.println(
                    "File upload status :: " + fileUploadResponse.getStatus()
            );
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onCompleted() {

        }
    }

}
