// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kfMessage.proto

package com.ty.chat.protocol;

public interface KfSendOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protocol.KfSend)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .protocol.MsgType msgType = 1 [default = MSG_TYPE_TEXT];</code>
   * @return Whether the msgType field is set.
   */
  boolean hasMsgType();
  /**
   * <code>optional .protocol.MsgType msgType = 1 [default = MSG_TYPE_TEXT];</code>
   * @return The msgType.
   */
  com.ty.chat.protocol.MsgType getMsgType();

  /**
   * <code>optional string timeStamp = 2;</code>
   * @return Whether the timeStamp field is set.
   */
  boolean hasTimeStamp();
  /**
   * <code>optional string timeStamp = 2;</code>
   * @return The timeStamp.
   */
  java.lang.String getTimeStamp();
  /**
   * <code>optional string timeStamp = 2;</code>
   * @return The bytes for timeStamp.
   */
  com.google.protobuf.ByteString
      getTimeStampBytes();

  /**
   * <code>required string content = 3;</code>
   * @return Whether the content field is set.
   */
  boolean hasContent();
  /**
   * <code>required string content = 3;</code>
   * @return The content.
   */
  java.lang.String getContent();
  /**
   * <code>required string content = 3;</code>
   * @return The bytes for content.
   */
  com.google.protobuf.ByteString
      getContentBytes();

  /**
   * <code>required string fromId = 4;</code>
   * @return Whether the fromId field is set.
   */
  boolean hasFromId();
  /**
   * <code>required string fromId = 4;</code>
   * @return The fromId.
   */
  java.lang.String getFromId();
  /**
   * <code>required string fromId = 4;</code>
   * @return The bytes for fromId.
   */
  com.google.protobuf.ByteString
      getFromIdBytes();

  /**
   * <code>optional string extras = 5;</code>
   * @return Whether the extras field is set.
   */
  boolean hasExtras();
  /**
   * <code>optional string extras = 5;</code>
   * @return The extras.
   */
  java.lang.String getExtras();
  /**
   * <code>optional string extras = 5;</code>
   * @return The bytes for extras.
   */
  com.google.protobuf.ByteString
      getExtrasBytes();

  /**
   * <code>required string sessionId = 6;</code>
   * @return Whether the sessionId field is set.
   */
  boolean hasSessionId();
  /**
   * <code>required string sessionId = 6;</code>
   * @return The sessionId.
   */
  java.lang.String getSessionId();
  /**
   * <code>required string sessionId = 6;</code>
   * @return The bytes for sessionId.
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();

  /**
   * <code>optional int32 id = 7;</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <code>optional int32 id = 7;</code>
   * @return The id.
   */
  int getId();
}
