// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kfMessage.proto

package com.ty.chat.protocol;

public interface KfMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protocol.KfMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required .protocol.Command cmd = 1;</code>
   * @return Whether the cmd field is set.
   */
  boolean hasCmd();
  /**
   * <code>required .protocol.Command cmd = 1;</code>
   * @return The cmd.
   */
  com.ty.chat.protocol.Command getCmd();

  /**
   * <pre>
   * 版本信息
   * </pre>
   *
   * <code>optional string version = 2 [default = "1.0.0"];</code>
   * @return Whether the version field is set.
   */
  boolean hasVersion();
  /**
   * <pre>
   * 版本信息
   * </pre>
   *
   * <code>optional string version = 2 [default = "1.0.0"];</code>
   * @return The version.
   */
  java.lang.String getVersion();
  /**
   * <pre>
   * 版本信息
   * </pre>
   *
   * <code>optional string version = 2 [default = "1.0.0"];</code>
   * @return The bytes for version.
   */
  com.google.protobuf.ByteString
      getVersionBytes();

  /**
   * <code>optional .protocol.KfReq kfReq = 3;</code>
   * @return Whether the kfReq field is set.
   */
  boolean hasKfReq();
  /**
   * <code>optional .protocol.KfReq kfReq = 3;</code>
   * @return The kfReq.
   */
  com.ty.chat.protocol.KfReq getKfReq();
  /**
   * <code>optional .protocol.KfReq kfReq = 3;</code>
   */
  com.ty.chat.protocol.KfReqOrBuilder getKfReqOrBuilder();

  /**
   * <code>optional .protocol.KfSend kfSend = 4;</code>
   * @return Whether the kfSend field is set.
   */
  boolean hasKfSend();
  /**
   * <code>optional .protocol.KfSend kfSend = 4;</code>
   * @return The kfSend.
   */
  com.ty.chat.protocol.KfSend getKfSend();
  /**
   * <code>optional .protocol.KfSend kfSend = 4;</code>
   */
  com.ty.chat.protocol.KfSendOrBuilder getKfSendOrBuilder();

  /**
   * <code>optional .protocol.KfIsReaded kfIsReaded = 5;</code>
   * @return Whether the kfIsReaded field is set.
   */
  boolean hasKfIsReaded();
  /**
   * <code>optional .protocol.KfIsReaded kfIsReaded = 5;</code>
   * @return The kfIsReaded.
   */
  com.ty.chat.protocol.KfIsReaded getKfIsReaded();
  /**
   * <code>optional .protocol.KfIsReaded kfIsReaded = 5;</code>
   */
  com.ty.chat.protocol.KfIsReadedOrBuilder getKfIsReadedOrBuilder();

  /**
   * <code>optional .protocol.KfAckAndCancel kfAckAndCancel = 6;</code>
   * @return Whether the kfAckAndCancel field is set.
   */
  boolean hasKfAckAndCancel();
  /**
   * <code>optional .protocol.KfAckAndCancel kfAckAndCancel = 6;</code>
   * @return The kfAckAndCancel.
   */
  com.ty.chat.protocol.KfAckAndCancel getKfAckAndCancel();
  /**
   * <code>optional .protocol.KfAckAndCancel kfAckAndCancel = 6;</code>
   */
  com.ty.chat.protocol.KfAckAndCancelOrBuilder getKfAckAndCancelOrBuilder();

  /**
   * <code>optional .protocol.KfWithdraw kfWithdraw = 7;</code>
   * @return Whether the kfWithdraw field is set.
   */
  boolean hasKfWithdraw();
  /**
   * <code>optional .protocol.KfWithdraw kfWithdraw = 7;</code>
   * @return The kfWithdraw.
   */
  com.ty.chat.protocol.KfWithdraw getKfWithdraw();
  /**
   * <code>optional .protocol.KfWithdraw kfWithdraw = 7;</code>
   */
  com.ty.chat.protocol.KfWithdrawOrBuilder getKfWithdrawOrBuilder();

  /**
   * <code>optional .protocol.KfHeartbeat kfHeartbeat = 8;</code>
   * @return Whether the kfHeartbeat field is set.
   */
  boolean hasKfHeartbeat();
  /**
   * <code>optional .protocol.KfHeartbeat kfHeartbeat = 8;</code>
   * @return The kfHeartbeat.
   */
  com.ty.chat.protocol.KfHeartbeat getKfHeartbeat();
  /**
   * <code>optional .protocol.KfHeartbeat kfHeartbeat = 8;</code>
   */
  com.ty.chat.protocol.KfHeartbeatOrBuilder getKfHeartbeatOrBuilder();

  /**
   * <code>optional .protocol.KfSession kfSession = 9;</code>
   * @return Whether the kfSession field is set.
   */
  boolean hasKfSession();
  /**
   * <code>optional .protocol.KfSession kfSession = 9;</code>
   * @return The kfSession.
   */
  com.ty.chat.protocol.KfSession getKfSession();
  /**
   * <code>optional .protocol.KfSession kfSession = 9;</code>
   */
  com.ty.chat.protocol.KfSessionOrBuilder getKfSessionOrBuilder();

  /**
   * <code>optional .protocol.KfMessageResp kfMessageResp = 10;</code>
   * @return Whether the kfMessageResp field is set.
   */
  boolean hasKfMessageResp();
  /**
   * <code>optional .protocol.KfMessageResp kfMessageResp = 10;</code>
   * @return The kfMessageResp.
   */
  com.ty.chat.protocol.KfMessageResp getKfMessageResp();
  /**
   * <code>optional .protocol.KfMessageResp kfMessageResp = 10;</code>
   */
  com.ty.chat.protocol.KfMessageRespOrBuilder getKfMessageRespOrBuilder();

  /**
   * <code>optional .protocol.KfUnendMsg kfUnendMsg = 11;</code>
   * @return Whether the kfUnendMsg field is set.
   */
  boolean hasKfUnendMsg();
  /**
   * <code>optional .protocol.KfUnendMsg kfUnendMsg = 11;</code>
   * @return The kfUnendMsg.
   */
  com.ty.chat.protocol.KfUnendMsg getKfUnendMsg();
  /**
   * <code>optional .protocol.KfUnendMsg kfUnendMsg = 11;</code>
   */
  com.ty.chat.protocol.KfUnendMsgOrBuilder getKfUnendMsgOrBuilder();

  /**
   * <code>optional .protocol.KfOnline kfOnline = 12;</code>
   * @return Whether the kfOnline field is set.
   */
  boolean hasKfOnline();
  /**
   * <code>optional .protocol.KfOnline kfOnline = 12;</code>
   * @return The kfOnline.
   */
  com.ty.chat.protocol.KfOnline getKfOnline();
  /**
   * <code>optional .protocol.KfOnline kfOnline = 12;</code>
   */
  com.ty.chat.protocol.KfOnlineOrBuilder getKfOnlineOrBuilder();
}
