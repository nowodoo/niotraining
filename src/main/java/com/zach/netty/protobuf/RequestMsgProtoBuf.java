// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RequestMsg.proto

package com.zach.netty.protobuf;

public final class RequestMsgProtoBuf {
  private RequestMsgProtoBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RequestMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:RequestMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string cmd = 1;</code>
     */
    boolean hasCmd();
    /**
     * <code>required string cmd = 1;</code>
     */
    java.lang.String getCmd();
    /**
     * <code>required string cmd = 1;</code>
     */
    com.google.protobuf.ByteString
        getCmdBytes();

    /**
     * <code>required bytes requestParam = 2;</code>
     */
    boolean hasRequestParam();
    /**
     * <code>required bytes requestParam = 2;</code>
     */
    com.google.protobuf.ByteString getRequestParam();
  }
  /**
   * Protobuf type {@code RequestMsg}
   */
  public static final class RequestMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:RequestMsg)
      RequestMsgOrBuilder {
    // Use RequestMsg.newBuilder() to construct.
    private RequestMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RequestMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RequestMsg defaultInstance;
    public static RequestMsg getDefaultInstance() {
      return defaultInstance;
    }

    public RequestMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RequestMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              cmd_ = bs;
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              requestParam_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.zach.netty.protobuf.RequestMsgProtoBuf.internal_static_RequestMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.zach.netty.protobuf.RequestMsgProtoBuf.internal_static_RequestMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.class, com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<RequestMsg> PARSER =
        new com.google.protobuf.AbstractParser<RequestMsg>() {
      public RequestMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RequestMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RequestMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CMD_FIELD_NUMBER = 1;
    private java.lang.Object cmd_;
    /**
     * <code>required string cmd = 1;</code>
     */
    public boolean hasCmd() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string cmd = 1;</code>
     */
    public java.lang.String getCmd() {
      java.lang.Object ref = cmd_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          cmd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string cmd = 1;</code>
     */
    public com.google.protobuf.ByteString
        getCmdBytes() {
      java.lang.Object ref = cmd_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cmd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int REQUESTPARAM_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString requestParam_;
    /**
     * <code>required bytes requestParam = 2;</code>
     */
    public boolean hasRequestParam() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required bytes requestParam = 2;</code>
     */
    public com.google.protobuf.ByteString getRequestParam() {
      return requestParam_;
    }

    private void initFields() {
      cmd_ = "";
      requestParam_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasCmd()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRequestParam()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getCmdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, requestParam_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getCmdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, requestParam_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code RequestMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:RequestMsg)
        com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.zach.netty.protobuf.RequestMsgProtoBuf.internal_static_RequestMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.zach.netty.protobuf.RequestMsgProtoBuf.internal_static_RequestMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.class, com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.Builder.class);
      }

      // Construct using com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        cmd_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        requestParam_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.zach.netty.protobuf.RequestMsgProtoBuf.internal_static_RequestMsg_descriptor;
      }

      public com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg getDefaultInstanceForType() {
        return com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.getDefaultInstance();
      }

      public com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg build() {
        com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg buildPartial() {
        com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg result = new com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.cmd_ = cmd_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.requestParam_ = requestParam_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg) {
          return mergeFrom((com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg other) {
        if (other == com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg.getDefaultInstance()) return this;
        if (other.hasCmd()) {
          bitField0_ |= 0x00000001;
          cmd_ = other.cmd_;
          onChanged();
        }
        if (other.hasRequestParam()) {
          setRequestParam(other.getRequestParam());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCmd()) {
          
          return false;
        }
        if (!hasRequestParam()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.zach.netty.protobuf.RequestMsgProtoBuf.RequestMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object cmd_ = "";
      /**
       * <code>required string cmd = 1;</code>
       */
      public boolean hasCmd() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string cmd = 1;</code>
       */
      public java.lang.String getCmd() {
        java.lang.Object ref = cmd_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            cmd_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string cmd = 1;</code>
       */
      public com.google.protobuf.ByteString
          getCmdBytes() {
        java.lang.Object ref = cmd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          cmd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string cmd = 1;</code>
       */
      public Builder setCmd(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        cmd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string cmd = 1;</code>
       */
      public Builder clearCmd() {
        bitField0_ = (bitField0_ & ~0x00000001);
        cmd_ = getDefaultInstance().getCmd();
        onChanged();
        return this;
      }
      /**
       * <code>required string cmd = 1;</code>
       */
      public Builder setCmdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        cmd_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString requestParam_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>required bytes requestParam = 2;</code>
       */
      public boolean hasRequestParam() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required bytes requestParam = 2;</code>
       */
      public com.google.protobuf.ByteString getRequestParam() {
        return requestParam_;
      }
      /**
       * <code>required bytes requestParam = 2;</code>
       */
      public Builder setRequestParam(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        requestParam_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bytes requestParam = 2;</code>
       */
      public Builder clearRequestParam() {
        bitField0_ = (bitField0_ & ~0x00000002);
        requestParam_ = getDefaultInstance().getRequestParam();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:RequestMsg)
    }

    static {
      defaultInstance = new RequestMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:RequestMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RequestMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_RequestMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020RequestMsg.proto\"/\n\nRequestMsg\022\013\n\003cmd\030" +
      "\001 \002(\t\022\024\n\014requestParam\030\002 \002(\014B-\n\027com.zach." +
      "netty.protobufB\022RequestMsgProtoBuf"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_RequestMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RequestMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_RequestMsg_descriptor,
        new java.lang.String[] { "Cmd", "RequestParam", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
