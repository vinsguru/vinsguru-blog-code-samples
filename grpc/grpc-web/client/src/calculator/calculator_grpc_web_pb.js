/**
 * @fileoverview gRPC-Web generated client stub for calculator
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.calculator = require('./calculator_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.calculator.CalculatorServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.calculator.CalculatorServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.calculator.Input,
 *   !proto.calculator.Output>}
 */
const methodDescriptor_CalculatorService_findSquare = new grpc.web.MethodDescriptor(
  '/calculator.CalculatorService/findSquare',
  grpc.web.MethodType.UNARY,
  proto.calculator.Input,
  proto.calculator.Output,
  /**
   * @param {!proto.calculator.Input} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.calculator.Output.deserializeBinary
);


/**
 * @param {!proto.calculator.Input} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.calculator.Output)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.calculator.Output>|undefined}
 *     The XHR Node Readable Stream
 */
proto.calculator.CalculatorServiceClient.prototype.findSquare =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/calculator.CalculatorService/findSquare',
      request,
      metadata || {},
      methodDescriptor_CalculatorService_findSquare,
      callback);
};


/**
 * @param {!proto.calculator.Input} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.calculator.Output>}
 *     Promise that resolves to the response
 */
proto.calculator.CalculatorServicePromiseClient.prototype.findSquare =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/calculator.CalculatorService/findSquare',
      request,
      metadata || {},
      methodDescriptor_CalculatorService_findSquare);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.calculator.Input,
 *   !proto.calculator.Output>}
 */
const methodDescriptor_CalculatorService_findFactors = new grpc.web.MethodDescriptor(
  '/calculator.CalculatorService/findFactors',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.calculator.Input,
  proto.calculator.Output,
  /**
   * @param {!proto.calculator.Input} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.calculator.Output.deserializeBinary
);


/**
 * @param {!proto.calculator.Input} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.calculator.Output>}
 *     The XHR Node Readable Stream
 */
proto.calculator.CalculatorServiceClient.prototype.findFactors =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/calculator.CalculatorService/findFactors',
      request,
      metadata || {},
      methodDescriptor_CalculatorService_findFactors);
};


/**
 * @param {!proto.calculator.Input} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.calculator.Output>}
 *     The XHR Node Readable Stream
 */
proto.calculator.CalculatorServicePromiseClient.prototype.findFactors =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/calculator.CalculatorService/findFactors',
      request,
      metadata || {},
      methodDescriptor_CalculatorService_findFactors);
};


module.exports = proto.calculator;

