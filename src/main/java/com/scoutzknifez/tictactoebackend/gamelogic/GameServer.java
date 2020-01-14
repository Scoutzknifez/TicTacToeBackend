package com.scoutzknifez.tictactoebackend.gamelogic;

import com.scoutzknifez.tictactoebackend.utility.Utils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.Setter;
import com.scoutzknifez.tictactoebackend.structures.Player;
import lombok.SneakyThrows;

@Getter
@Setter
public class GameServer {
    private static int serverID = 0;

    private Player xPlayer;
    private Player oPlayer;

    @SneakyThrows
    public GameServer(Player x, Player o) {
        serverID++;

        setXPlayer(x);
        setOPlayer(o);

        try {
            boolean hasEpoll = Epoll.isAvailable();

            EventLoopGroup group = hasEpoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(group)
                    .channel(hasEpoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new InboundHandler());

            Channel channel = bootstrap.bind(5050).sync().channel();
            channel.closeFuture().addListener(c -> {
                group.shutdownGracefully();
                Utils.log("All channels closed and server shutdown!");
            });
        } catch (Exception e) {
            Utils.log("Failed to start server for server ", serverID);
        }
    }
}
