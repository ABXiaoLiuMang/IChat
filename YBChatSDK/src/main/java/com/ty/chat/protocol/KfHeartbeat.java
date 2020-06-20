// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kfMessage.proto

package com.ty.chat.protocol;

/**
 * <pre>
 * 发送心跳
 * </pre>
 *
 * Protobuf type {@code protocol.KfHeartbeat}
 */
public  final class KfHeartbeat extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:protocol.KfHeartbeat)
    KfHeartbeatOrBuilder {
private static final long serialVersionUID = 0L;
  // Use KfHeartbeat.newBuilder() to construct.
  private KfHeartbeat(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private KfHeartbeat() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new KfHeartbeat();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private KfHeartbeat(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 8: {
            bitField0_ |= 0x00000001;
            id_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            ack_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.ty.chat.protocol.KfMessageOuterClass.internal_static_protocol_KfHeartbeat_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.ty.chat.protocol.KfMessageOuterClass.internal_static_protocol_KfHeartbeat_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.ty.chat.protocol.KfHeartbeat.class, com.ty.chat.protocol.KfHeartbeat.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>required int32 id = 1;</code>
   * @return Whether the id field is set.
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required int32 id = 1;</code>
   * @return The id.
   */
  public int getId() {
    return id_;
  }

  public static final int ACK_FIELD_NUMBER = 2;
  private int ack_;
  /**
   * <code>required int32 ack = 2;</code>
   * @return Whether the ack field is set.
   */
  public boolean hasAck() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required int32 ack = 2;</code>
   * @return The ack.
   */
  public int getAck() {
    return ack_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAck()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeInt32(1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeInt32(2, ack_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, ack_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.ty.chat.protocol.KfHeartbeat)) {
      return super.equals(obj);
    }
    com.ty.chat.protocol.KfHeartbeat other = (com.ty.chat.protocol.KfHeartbeat) obj;

    if (hasId() != other.hasId()) return false;
    if (hasId()) {
      if (getId()
          != other.getId()) return false;
    }
    if (hasAck() != other.hasAck()) return false;
    if (hasAck()) {
      if (getAck()
          != other.getAck()) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
    }
    if (hasAck()) {
      hash = (37 * hash) + ACK_FIELD_NUMBER;
      hash = (53 * hash) + getAck();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.ty.chat.protocol.KfHeartbeat parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.ty.chat.protocol.KfHeartbeat prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * 发送心跳
   * </pre>
   *
   * Protobuf type {@code protocol.KfHeartbeat}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:protocol.KfHeartbeat)
      com.ty.chat.protocol.KfHeartbeatOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ty.chat.protocol.KfMessageOuterClass.internal_static_protocol_KfHeartbeat_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ty.chat.protocol.KfMessageOuterClass.internal_static_protocol_KfHeartbeat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ty.chat.protocol.KfHeartbeat.class, com.ty.chat.protocol.KfHeartbeat.Builder.class);
    }

    // Construct using com.ty.chat.protocol.KfHeartbeat.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      ack_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.ty.chat.protocol.KfMessageOuterClass.internal_static_protocol_KfHeartbeat_descriptor;
    }

    @java.lang.Override
    public com.ty.chat.protocol.KfHeartbeat getDefaultInstanceForType() {
      return com.ty.chat.protocol.KfHeartbeat.getDefaultInstance();
    }

    @java.lang.Override
    public com.ty.chat.protocol.KfHeartbeat build() {
      com.ty.chat.protocol.KfHeartbeat result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.ty.chat.protocol.KfHeartbeat buildPartial() {
      com.ty.chat.protocol.KfHeartbeat result = new com.ty.chat.protocol.KfHeartbeat(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.ack_ = ack_;
        to_bitField0_ |= 0x00000002;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.ty.chat.protocol.KfHeartbeat) {
        return mergeFrom((com.ty.chat.protocol.KfHeartbeat)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.ty.chat.protocol.KfHeartbeat other) {
      if (other == com.ty.chat.protocol.KfHeartbeat.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasAck()) {
        setAck(other.getAck());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      if (!hasId()) {
        return false;
      }
      if (!hasAck()) {
        return false;
      }
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.ty.chat.protocol.KfHeartbeat parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.ty.chat.protocol.KfHeartbeat) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int id_ ;
    /**
     * <code>required int32 id = 1;</code>
     * @return Whether the id field is set.
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required int32 id = 1;</code>
     * @return The id.
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>required int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0;
      onChanged();
      return this;
    }

    private int ack_ ;
    /**
     * <code>required int32 ack = 2;</code>
     * @return Whether the ack field is set.
     */
    public boolean hasAck() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required int32 ack = 2;</code>
     * @return The ack.
     */
    public int getAck() {
      return ack_;
    }
    /**
     * <code>required int32 ack = 2;</code>
     * @param value The ack to set.
     * @return This builder for chaining.
     */
    public Builder setAck(int value) {
      bitField0_ |= 0x00000002;
      ack_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 ack = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAck() {
      bitField0_ = (bitField0_ & ~0x00000002);
      ack_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:protocol.KfHeartbeat)
  }

  // @@protoc_insertion_point(class_scope:protocol.KfHeartbeat)
  private static final com.ty.chat.protocol.KfHeartbeat DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.ty.chat.protocol.KfHeartbeat();
  }

  public static com.ty.chat.protocol.KfHeartbeat getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<KfHeartbeat>
      PARSER = new com.google.protobuf.AbstractParser<KfHeartbeat>() {
    @java.lang.Override
    public KfHeartbeat parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new KfHeartbeat(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<KfHeartbeat> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<KfHeartbeat> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.ty.chat.protocol.KfHeartbeat getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

