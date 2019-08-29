package com.zhang.webapi.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;


/**
 * @author zhen  2019-08-22 10:08
 * 接收/处理/响应客户端socket请求的核心处理业务类
 * MyWebSocketHandler
 **/
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    /**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启。。。。。");
    }

    /**
     * 客户端与服务端断开连接的时候调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭。。。。。");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 服务端处理客户端websocket请求的和新方法
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object o) throws Exception {
        if(o instanceof FullHttpRequest){
            handHttpRequest(ctx,(FullHttpRequest)o);
        }else if(o instanceof WebSocketFrame){
            handWebSocketFrame(ctx,(WebSocketFrame)o);
        }
    }

    /**
     * 处理客户端与服务端之前的websocket业务
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        // 判断是否是关闭websocket的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),((CloseWebSocketFrame) frame).retain());
        }
        // 判断是否是ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }

        // 判断是否是二进制消息，如果是二进制消息，则跑出异常
        if(!(frame instanceof TextWebSocketFrame)){
            System.out.println("目前不支持二进制消息");
            throw new RuntimeException("["+this.getClass().getName()+"]不支持消息");
        }

        // 返回应答消息
        // 返回客户端向服务端发送的消息
        String request = ((TextWebSocketFrame)frame).text();
        System.out.println("服务端收到客户端发送的消息===>>>"+request);
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()
                                                                            +ctx.channel().id()
                                                                            +" ====>>> "
                                                                            +request);
        NettyConfig.group.writeAndFlush(tws);
    }

    /**
     * 处理客户端向服务端发起http握手请求的业务
     */
    private void handHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        if(!req.getDecoderResult().isSuccess()||!"websocket".equals(req.headers().get("Upgrade"))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
        handshaker = wsFactory.newHandshaker(req);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(),req);
        }

    }

    /**
     * 服务端向客户端相应消息
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse response){
        if(response.getStatus().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if(response.getStatus().code() != 200){
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
