package com.scoutzknifez.tictactoebackend.gamelogic;

import com.scoutzknifez.tictactoebackend.utility.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class InboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        Utils.log(msg.toString(StandardCharsets.UTF_8));
    }
}
