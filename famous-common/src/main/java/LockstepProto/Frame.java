// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LSMessage.proto

package LockstepProto;

/**
 * Protobuf type {@code LockstepProto.Frame}
 */
public  final class Frame extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:LockstepProto.Frame)
    FrameOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Frame.newBuilder() to construct.
  private Frame(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Frame() {
    commands_ = java.util.Collections.emptyList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Frame();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Frame(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
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

            influenceFrameCount_ = input.readUInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              commands_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
              mutable_bitField0_ |= 0x00000001;
            }
            commands_.add(input.readBytes());
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        commands_ = java.util.Collections.unmodifiableList(commands_); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return LockstepProto.LSMessage.internal_static_LockstepProto_Frame_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return LockstepProto.LSMessage.internal_static_LockstepProto_Frame_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Frame.class, Builder.class);
  }

  public static final int INFLUENCEFRAMECOUNT_FIELD_NUMBER = 1;
  private int influenceFrameCount_;
  /**
   * <code>uint32 InfluenceFrameCount = 1;</code>
   */
  public int getInfluenceFrameCount() {
    return influenceFrameCount_;
  }

  public static final int COMMANDS_FIELD_NUMBER = 2;
  private java.util.List<com.google.protobuf.ByteString> commands_;
  /**
   * <code>repeated bytes commands = 2;</code>
   */
  public java.util.List<com.google.protobuf.ByteString>
      getCommandsList() {
    return commands_;
  }
  /**
   * <code>repeated bytes commands = 2;</code>
   */
  public int getCommandsCount() {
    return commands_.size();
  }
  /**
   * <code>repeated bytes commands = 2;</code>
   */
  public com.google.protobuf.ByteString getCommands(int index) {
    return commands_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (influenceFrameCount_ != 0) {
      output.writeUInt32(1, influenceFrameCount_);
    }
    for (int i = 0; i < commands_.size(); i++) {
      output.writeBytes(2, commands_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (influenceFrameCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(1, influenceFrameCount_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < commands_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(commands_.get(i));
      }
      size += dataSize;
      size += 1 * getCommandsList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof Frame)) {
      return super.equals(obj);
    }
    Frame other = (Frame) obj;

    if (getInfluenceFrameCount()
        != other.getInfluenceFrameCount()) return false;
    if (!getCommandsList()
        .equals(other.getCommandsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + INFLUENCEFRAMECOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getInfluenceFrameCount();
    if (getCommandsCount() > 0) {
      hash = (37 * hash) + COMMANDS_FIELD_NUMBER;
      hash = (53 * hash) + getCommandsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Frame parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Frame parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Frame parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Frame parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Frame parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Frame parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Frame parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Frame parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static Frame parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static Frame parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Frame parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Frame parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(Frame prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code LockstepProto.Frame}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:LockstepProto.Frame)
      LockstepProto.FrameOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return LockstepProto.LSMessage.internal_static_LockstepProto_Frame_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return LockstepProto.LSMessage.internal_static_LockstepProto_Frame_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Frame.class, Builder.class);
    }

    // Construct using LockstepProto.Frame.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      influenceFrameCount_ = 0;

      commands_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return LockstepProto.LSMessage.internal_static_LockstepProto_Frame_descriptor;
    }

    @Override
    public Frame getDefaultInstanceForType() {
      return Frame.getDefaultInstance();
    }

    @Override
    public Frame build() {
      Frame result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public Frame buildPartial() {
      Frame result = new Frame(this);
      int from_bitField0_ = bitField0_;
      result.influenceFrameCount_ = influenceFrameCount_;
      if (((bitField0_ & 0x00000001) != 0)) {
        commands_ = java.util.Collections.unmodifiableList(commands_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.commands_ = commands_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Frame) {
        return mergeFrom((Frame)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Frame other) {
      if (other == Frame.getDefaultInstance()) return this;
      if (other.getInfluenceFrameCount() != 0) {
        setInfluenceFrameCount(other.getInfluenceFrameCount());
      }
      if (!other.commands_.isEmpty()) {
        if (commands_.isEmpty()) {
          commands_ = other.commands_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureCommandsIsMutable();
          commands_.addAll(other.commands_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Frame parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (Frame) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int influenceFrameCount_ ;
    /**
     * <code>uint32 InfluenceFrameCount = 1;</code>
     */
    public int getInfluenceFrameCount() {
      return influenceFrameCount_;
    }
    /**
     * <code>uint32 InfluenceFrameCount = 1;</code>
     */
    public Builder setInfluenceFrameCount(int value) {
      
      influenceFrameCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 InfluenceFrameCount = 1;</code>
     */
    public Builder clearInfluenceFrameCount() {
      
      influenceFrameCount_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.google.protobuf.ByteString> commands_ = java.util.Collections.emptyList();
    private void ensureCommandsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        commands_ = new java.util.ArrayList<com.google.protobuf.ByteString>(commands_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public java.util.List<com.google.protobuf.ByteString>
        getCommandsList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(commands_) : commands_;
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public int getCommandsCount() {
      return commands_.size();
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public com.google.protobuf.ByteString getCommands(int index) {
      return commands_.get(index);
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public Builder setCommands(
        int index, com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCommandsIsMutable();
      commands_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public Builder addCommands(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCommandsIsMutable();
      commands_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public Builder addAllCommands(
        Iterable<? extends com.google.protobuf.ByteString> values) {
      ensureCommandsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, commands_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated bytes commands = 2;</code>
     */
    public Builder clearCommands() {
      commands_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:LockstepProto.Frame)
  }

  // @@protoc_insertion_point(class_scope:LockstepProto.Frame)
  private static final Frame DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Frame();
  }

  public static Frame getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Frame>
      PARSER = new com.google.protobuf.AbstractParser<Frame>() {
    @Override
    public Frame parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Frame(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Frame> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Frame> getParserForType() {
    return PARSER;
  }

  @Override
  public Frame getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

